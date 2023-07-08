package com.musinsa.menu.biz.menu.domain.service;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.repository.MenuRepository;
import com.musinsa.menu.biz.menu.exception.NoSuchMenuException;
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

    @Transactional(readOnly = true)
    public boolean existsById(final Long id) {
        return menuRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public Menu findById(final Long id) {
        return menuRepository.findById(id).orElseThrow(
            () -> new NoSuchMenuException("존재하지 않는 메뉴 ID 입니다.")
        );
    }

}
