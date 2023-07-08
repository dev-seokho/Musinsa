package com.musinsa.menu.biz.menu.service;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.MenuRequest;
import com.musinsa.menu.biz.menu.dto.response.MenuResponse;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuLinkException;
import com.musinsa.menu.biz.menu.exception.DuplicateMenuTitleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDomainService menuDomainService;

    @Transactional
    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = Menu.builder()
            .title(menuRequest.title())
            .link(menuRequest.link())
            .build();

        if (menuDomainService.existsByTitle(menuRequest.title())) {
            throw new DuplicateMenuTitleException("메뉴 타이틀 중복입니다.");
        }

        if (menuDomainService.existsByLink(menuRequest.link())) {
            throw new DuplicateMenuLinkException("메뉴 링크 중복입니다.");
        }

        Long menuId = menuDomainService.save(menu);
        return MenuResponse.builder()
            .id(menuId)
            .title(menu.getTitle())
            .link(menu.getLink())
            .build();
    }

    @Transactional
    public void deleteMenu(Long menuId) {
        Menu menu = menuDomainService.get(menuId);
        menuDomainService.delete(menu);
    }
}
