package com.musinsa.menu.biz.menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.domain.service.SubMenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.SubMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.SubMenuResponse;
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
    void createMenuTest() {
        //given
        Long menuId = 1L;
        Long subMenuId = 1L;
        SubMenuRequest subMenuRequest = SubMenuRequest.builder()
            .title("니트")
            .build();

        when(menuDomainService.existsById(menuId)).thenReturn(true);
        when(subMenuDomainService.existsByTitle(subMenuRequest.title())).thenReturn(false);
        when(subMenuDomainService.save(any(SubMenu.class))).thenReturn(subMenuId);

        SubMenuResponse expectMenuResponse = SubMenuResponse.builder()
            .id(subMenuId)
            .menuId(menuId)
            .title(subMenuRequest.title())
            .build();

        //when
        SubMenuResponse subMenuResponse = subMenuService.createSubMenu(subMenuRequest, menuId);

        //then
        assertThat(subMenuResponse).isEqualTo(expectMenuResponse);
    }


}
