package com.musinsa.menu.biz.menu.domain.service;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.repository.SubMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubMenuDomainService {

    private final SubMenuRepository subMenuRepository;

    @Transactional
    public Long save(final SubMenu subMenu) {
        return subMenuRepository.save(subMenu).getId();
    }

    @Transactional(readOnly = true)
    public boolean existsByTitle(final String title) {
        return subMenuRepository.existsByTitle(title);
    }

}
