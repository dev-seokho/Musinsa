package com.musinsa.menu.biz.menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.domain.service.SubMenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.CreateSubMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.CreateSubMenuResponse;
import com.musinsa.menu.biz.menu.exception.AlreadyExistsMenuException;
import com.musinsa.menu.biz.menu.exception.DuplicateSubMenuTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubMenuServiceTest {

    @InjectMocks
    SubMenuService subMenuService;
    @Mock
    MenuDomainService menuDomainService;
    @Mock
    SubMenuDomainService subMenuDomainService;

    @Test
    @DisplayName("서브 메뉴 등록 성공")
    void createSubMenuTest() {
        //given
        Long menuId = 1L;
        Long subMenuId = 1L;
        CreateSubMenuRequest createSubMenuRequest = CreateSubMenuRequest.builder()
            .title("니트")
            .build();

        when(menuDomainService.exists(menuId)).thenReturn(true);
        when(subMenuDomainService.existsByTitle(createSubMenuRequest.title())).thenReturn(false);
        when(subMenuDomainService.save(any(SubMenu.class))).thenReturn(subMenuId);

        CreateSubMenuResponse expectMenuResponse = CreateSubMenuResponse.builder()
            .id(subMenuId)
            .menuId(menuId)
            .title(createSubMenuRequest.title())
            .build();

        //when
        CreateSubMenuResponse createSubMenuResponse = subMenuService
            .createSubMenu(createSubMenuRequest, menuId);

        //then
        assertThat(createSubMenuResponse).isEqualTo(expectMenuResponse);
    }

    @Test
    @DisplayName("서브 메뉴 등록 실패 - 존재하지 않는 메뉴 ID 일 때")
    void subMenuCreationFailureWhenDoesNotExistsMenuTest() {
        //given
        Long menuId = 1L;
        CreateSubMenuRequest createSubMenuRequest = CreateSubMenuRequest.builder()
            .title("상의")
            .build();

        when(menuDomainService.exists(menuId)).thenReturn(false);

        //then
        assertThrows(AlreadyExistsMenuException.class,
            //when
            () -> subMenuService.createSubMenu(createSubMenuRequest, menuId)
        );
    }

    @Test
    @DisplayName("서브 메뉴 등록 실패 - 서브 메뉴 타이틀이 중복일 때")
    void subMenuCreationFailureWhenTitleIsDuplicateTest() {
        //given
        Long menuId = 1L;
        CreateSubMenuRequest createSubMenuRequest = CreateSubMenuRequest.builder()
            .title("상의")
            .build();

        when(menuDomainService.exists(menuId)).thenReturn(true);
        when(subMenuDomainService.existsByTitle(createSubMenuRequest.title())).thenReturn(true);

        //then
        assertThrows(DuplicateSubMenuTitleException.class,
            //when
            () -> subMenuService.createSubMenu(createSubMenuRequest, menuId)
        );
    }
}
