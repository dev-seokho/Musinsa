package com.musinsa.menu.biz.menu.domain.repository;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubMenuRepository extends JpaRepository<SubMenu, Long> {

    boolean existsByTitle(String title);
}
