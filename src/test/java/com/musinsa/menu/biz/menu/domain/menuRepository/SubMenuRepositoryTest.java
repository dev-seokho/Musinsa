package com.musinsa.menu.biz.menu.domain.menuRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.entity.SubMenu;
import com.musinsa.menu.biz.menu.domain.repository.MenuRepository;
import com.musinsa.menu.biz.menu.domain.repository.SubMenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureTestDatabase(connection = H2)
@DataJpaTest
@ActiveProfiles("test")
public class SubMenuRepositoryTest {

    @Autowired
    SubMenuRepository subMenuRepository;
    @Autowired
    MenuRepository menuRepository;
    long menuId;

    @BeforeEach
    void beforeEach() {
        Menu menu = Menu.builder()
            .title("상의")
            .link("/top")
            .build();

        this.menuId = menuRepository.save(menu).getId();
    }

    @Test
    @DisplayName("서브 메뉴에 이미 타이틀이 존재할 때 true 반환")
    void existsByTitleTest() {
        //given
        String title = "니트";

        SubMenu subMenu = SubMenu.builder()
            .menuId(this.menuId)
            .title(title)
            .build();

        subMenuRepository.save(subMenu);

        //when
        boolean existsTitle = subMenuRepository.existsByTitle(title);

        //then
        assertTrue(existsTitle);
    }

    @Test
    @DisplayName("서브 메뉴에 타이틀이 존재하지 않을 때 false 반환")
    void doesNotExistsByTitleTest() {
        //given
        String title = "니트";

        //when
        boolean existsTitle = subMenuRepository.existsByTitle(title);

        //then
        assertFalse(existsTitle);
    }
}
