package com.musinsa.menu.biz.menu.service;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.service.MenuDomainService;
import com.musinsa.menu.biz.menu.domain.service.SubMenuDomainService;
import com.musinsa.menu.biz.menu.dto.request.SubMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.SubMenuResponse;
import com.musinsa.menu.biz.menu.exception.AlreadyExistsMenuException;
import com.musinsa.menu.biz.menu.exception.DuplicateSubMenuTitleException;
import com.musinsa.menu.biz.menu.exception.NoSuchMenuException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubMenuService {

    private final MenuDomainService menuDomainService;
    private final SubMenuDomainService subMenuDomainService;

    @Transactional
    public SubMenuResponse createSubMenu(SubMenuRequest subMenuRequest, Long menuId) {
        if (!menuDomainService.existsById(menuId)) {
            throw new AlreadyExistsMenuException("이미 존재하는 메뉴 ID 입니다.");
        }

        if (subMenuDomainService.existsByTitle(subMenuRequest.title())) {
            throw new DuplicateSubMenuTitleException("서브 메뉴 타이틀 중복입니다.");
        }

        SubMenu subMenu = SubMenu.builder()
            .menuId(menuId)
            .title(subMenuRequest.title())
            .build();

        Long subMenuId = subMenuDomainService.save(subMenu);

        return SubMenuResponse.builder()
            .id(subMenuId)
            .menuId(subMenu.getMenuId())
            .title(subMenu.getTitle())
            .build();
    }
}
