package com.musinsa.menu.biz.menu.domain.repository;

import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SubMenuRepository extends JpaRepository<SubMenu, Long> {

    boolean existsByTitle(@Param("title") String title);
}
