package com.musinsa.menu.biz.menu.domain.repository;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    boolean existsByTitle(String title);
    boolean existsByLink(String link);
    Slice<Menu> findSliceBy(PageRequest pageRequest);
}
