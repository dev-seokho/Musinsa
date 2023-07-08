package com.musinsa.menu.biz.menu.domain.service;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuDomainService {

    private final MenuRepository menuRepository;

    @Transactional
    public Long save(final Menu menu) {
        return menuRepository.save(menu).getId();
    }

    @Transactional(readOnly = true)
    public boolean existsByTitle(final String title) {
        return menuRepository.existsByTitle(title);
    }

    @Transactional(readOnly = true)
    public boolean existsByLink(final String link) {
        return menuRepository.existsByLink(link);
    }
}
