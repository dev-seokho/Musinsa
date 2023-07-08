package com.musinsa.menu.biz.menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.MenuRequest;
import com.musinsa.menu.biz.menu.dto.response.MenuResponse;
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
    void MenuCreationFailureWhenTitleIsDuplicateTest() {
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
}
