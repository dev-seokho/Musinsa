package com.musinsa.menu.biz.menu.domain.service;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.repository.SubMenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubMenuDomainService {

    private final SubMenuRepository subMenuRepository;

    @Transactional
    public Long save(final SubMenu subMenu) {
        return subMenuRepository.save(subMenu).getId();
    }

    @Transactional
    public void deleteAll(final List<SubMenu> subMenus) {
        subMenuRepository.deleteAll(subMenus);
    }

    public boolean existsByTitle(final String title) {
        return subMenuRepository.existsByTitle(title);
    }
}
