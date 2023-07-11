package com.musinsa.menu.biz.menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.domain.service.SubMenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.CreateMenuRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateBannerRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.CreateMenuResponse;
import com.musinsa.menu.biz.menu.dto.response.MenuInfoResponse;
import com.musinsa.menu.biz.menu.dto.response.SubMenuResponse;
import com.musinsa.menu.biz.menu.dto.response.UpdateBannerResponse;
import com.musinsa.menu.biz.menu.dto.response.UpdateMenuResponse;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuLinkException;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuTitleException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

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
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(createMenuRequest.title())).thenReturn(false);
        when(menuDomainService.existsByLink(createMenuRequest.link())).thenReturn(false);
        when(menuDomainService.save(any(Menu.class))).thenReturn(menuId);

        CreateMenuResponse expectCreateMenuResponse = CreateMenuResponse.builder()
            .id(menuId)
            .title(createMenuRequest.title())
            .link(createMenuRequest.link())
            .build();

        //when
        CreateMenuResponse createMenuResponse = menuService.createMenu(createMenuRequest);

        //then
        assertThat(createMenuResponse).isEqualTo(expectCreateMenuResponse);
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 메뉴 타이틀이 중복일 때")
    void menuCreationFailureWhenTitleIsDuplicateTest() {
        //given
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(createMenuRequest.title())).thenReturn(true);

        //then
        assertThrows(DuplicateMenuTitleException.class,
            //when
            () -> menuService.createMenu(createMenuRequest)
        );
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 메뉴 링크가 중복일 때")
    void menuCreationFailureWhenLinkIsDuplicateTest() {
        //given
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title("상의")
            .link("/link")
            .build();

        when(menuDomainService.existsByTitle(createMenuRequest.title())).thenReturn(false);
        when(menuDomainService.existsByLink(createMenuRequest.link())).thenReturn(true);

        //then
        assertThrows(DuplicateMenuLinkException.class,
            //when
            () -> menuService.createMenu(createMenuRequest)
        );
    }

    @Test
    @DisplayName("메뉴 조회 성공")
    void getMenuTest() {
        //given
        Long menuId = 1L;
        List<SubMenu> subMenus = new ArrayList<>();
        SubMenu subMenu1 = SubMenu.builder()
            .id(1L)
            .menuId(menuId)
            .title("Submenu 1")
            .build();

        SubMenu subMenu2 = SubMenu.builder()
            .id(2L)
            .menuId(menuId)
            .title("Submenu 2")
            .build();

        subMenus.add(subMenu1);
        subMenus.add(subMenu2);

        Menu menu = Menu.builder()
            .id(menuId)
            .title("상의")
            .link("/top")
            .bannerImageUrl("http://example.com/banner.jpg")
            .subMenus(subMenus)
            .build();

        when(menuDomainService.get(menuId)).thenReturn(menu);

        //when
        MenuInfoResponse menuInfoResponse = menuService.getMenu(menuId);

        //then
        assertThat(menuInfoResponse.id()).isEqualTo(menuId);
        assertThat(menuInfoResponse.title()).isEqualTo(menu.getTitle());
        assertThat(menuInfoResponse.link()).isEqualTo(menu.getLink());
        assertThat(menuInfoResponse.bannerImageUrl()).isEqualTo(menu.getBannerImageUrl());

        List<SubMenuResponse> subMenuResponses = menuInfoResponse.subMenuResponses();
        assertThat(subMenuResponses.size()).isEqualTo(2);
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

        UpdateMenuResponse expectUpdateMenuResponse = UpdateMenuResponse.builder()
            .id(menuId)
            .title(updateMenuRequest.title())
            .link(updateMenuRequest.link())
            .build();

        //when
        UpdateMenuResponse updateMenuResponse = menuService.updateMenu(updateMenuRequest, menuId);

        //then
        assertThat(updateMenuResponse).isEqualTo(expectUpdateMenuResponse);
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

    @Test
    @DisplayName("배너 추가 성공")
    void updateBannerTest() {
        //given
        Long menuId = 1L;
        String banner = "https://image.msscdn.net/mfile_s01/_brand/free_medium/musinsastandard.png";
        UpdateBannerRequest updateBannerRequest = UpdateBannerRequest.builder()
            .bannerImageUrl(banner)
            .build();

        Menu menu = Menu.builder()
            .id(menuId)
            .title("상의")
            .link("/top")
            .build();

        UpdateBannerResponse expectUpdateBannerResponse = UpdateBannerResponse.builder()
            .id(menuId)
            .title(menu.getTitle())
            .link(menu.getLink())
            .bannerImageUrl(updateBannerRequest.bannerImageUrl())
            .build();

        when(menuDomainService.get(menuId)).thenReturn(menu);

        //when
        UpdateBannerResponse updateBannerResponse = menuService
            .updateBanner(updateBannerRequest, menuId);

        //then
        assertThat(updateBannerResponse).isEqualTo(expectUpdateBannerResponse);
    }
}
