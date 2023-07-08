package com.musinsa.menu.biz.menu.domain.service;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.repository.MenuRepository;
import com.musinsa.menu.biz.menu.exception.NoSuchMenuException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuDomainService {

    private final MenuRepository menuRepository;

    @Transactional
    public Long save(final Menu menu) {
        return menuRepository.save(menu).getId();
    }

    @Transactional
    public void delete(final Menu menu) {
        menuRepository.delete(menu);
    }

    public boolean existsByTitle(final String title) {
        return menuRepository.existsByTitle(title);
    }

    public boolean existsByLink(final String link) {
        return menuRepository.existsByLink(link);
    }

    public boolean existsById(final Long id) {
        return menuRepository.existsById(id);
    }

    public Menu findById(final Long id) {
        return menuRepository.findById(id).orElseThrow(
            () -> new NoSuchMenuException("존재하지 않는 메뉴 ID 입니다.")
        );
    }
}
