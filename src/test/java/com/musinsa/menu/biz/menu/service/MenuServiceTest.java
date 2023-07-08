package com.musinsa.menu.biz.menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.domain.service.SubMenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.MenuRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.MenuResponse;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuLinkException;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuTitleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @InjectMocks
    MenuService menuService;

    @Mock
    MenuDomainService menuDomainService;

    @Mock
    SubMenuDomainService subMenuDomainService;

    @Test
    @DisplayName("메뉴 등록 성공")
    void createMenuTest() {
        //given
        Long menuId = 1L;
        MenuRequest menuRequest = MenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(menuRequest.title())).thenReturn(false);
        when(menuDomainService.existsByLink(menuRequest.link())).thenReturn(false);
        when(menuDomainService.save(any(Menu.class))).thenReturn(menuId);

        MenuResponse expectMenuResponse = MenuResponse.builder()
            .id(menuId)
            .title(menuRequest.title())
            .link(menuRequest.link())
            .build();

        //when
        MenuResponse menuResponse = menuService.createMenu(menuRequest);

        //then
        assertThat(menuResponse).isEqualTo(expectMenuResponse);
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 메뉴 타이틀이 중복일 때")
    void menuCreationFailureWhenTitleIsDuplicateTest() {
        //given
        MenuRequest menuRequest = MenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(menuRequest.title())).thenReturn(true);

        //then
        assertThrows(DuplicateMenuTitleException.class,
            //when
            () -> menuService.createMenu(menuRequest)
        );
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 메뉴 링크가 중복일 때")
    void menuCreationFailureWhenLinkIsDuplicateTest() {
        //given
        MenuRequest menuRequest = MenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(menuRequest.title())).thenReturn(false);
        when(menuDomainService.existsByLink(menuRequest.link())).thenReturn(true);

        //then
        assertThrows(DuplicateMenuLinkException.class,
            //when
            () -> menuService.createMenu(menuRequest)
        );
    }

    @Test
    @DisplayName("메뉴 수정 성공")
    void updateMenuTest() {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title("상의")
            .link("/top")
            .build();

        Menu menu = Menu.builder()
            .id(menuId)
            .title("하의")
            .link("/bottom")
            .build();

        when(menuDomainService.existsByTitle(updateMenuRequest.title())).thenReturn(false);
        when(menuDomainService.existsByLink(updateMenuRequest.link())).thenReturn(false);
        when(menuDomainService.get(menuId)).thenReturn(menu);

        MenuResponse expectMenuResponse = MenuResponse.builder()
            .id(menuId)
            .title(updateMenuRequest.title())
            .link(updateMenuRequest.link())
            .build();

        //when
        MenuResponse menuResponse = menuService.updateMenu(updateMenuRequest, menuId);

        //then
        assertThat(menuResponse).isEqualTo(expectMenuResponse);
    }

    @Test
    @DisplayName("메뉴 수정 실패 - 메뉴 타이틀이 중복일 때")
    void menuUpdateFailureWhenTitleIsDuplicateTest() {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(updateMenuRequest.title())).thenReturn(true);

        //then
        assertThrows(DuplicateMenuTitleException.class,
            //when
            () -> menuService.updateMenu(updateMenuRequest, menuId)
        );
    }

    @Test
    @DisplayName("메뉴 수정 실패 - 메뉴 타이틀이 중복일 때")
    void menuUpdateFailureWhenLinkIsDuplicateTest() {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(updateMenuRequest.title())).thenReturn(false);
        when(menuDomainService.existsByLink(updateMenuRequest.link())).thenReturn(true);

        //then
        assertThrows(DuplicateMenuLinkException.class,
            //when
            () -> menuService.updateMenu(updateMenuRequest, menuId)
        );
    }

    @Test
    @DisplayName("메뉴 삭제 성공 행위 검증 테스트")
    void deleteMenuTest() {
        //given
        Long menuId = 1L;
        Menu menu = Menu.builder().build();

        when(menuDomainService.get(menuId)).thenReturn(menu);

        //when
        menuService.deleteMenu(menuId);

        //then
        verify(menuDomainService).get(menuId);
    }
}
