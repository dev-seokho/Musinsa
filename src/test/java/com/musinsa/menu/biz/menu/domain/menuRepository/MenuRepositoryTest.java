package com.musinsa.menu.biz.menu.domain.menuRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

import com.musinsa.menu.biz.menu.domain.entity.Menu;
import com.musinsa.menu.biz.menu.domain.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureTestDatabase(connection = H2)
@DataJpaTest
@ActiveProfiles("test")
public class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    @DisplayName("메뉴에 이미 타이틀이 존재할 때 true 반환")
    void existsByTitleTest() {
        //given
        String title = "상의";
        Menu menu = Menu.builder()
            .title(title)
            .link("/top")
            .build();

        menuRepository.save(menu);

        //when
        boolean existsTitle = menuRepository.existsByTitle(title);

        //then
        assertTrue(existsTitle);
    }

    @Test
    @DisplayName("메뉴에 타이틀이 존재하지 않을 때 false 반환")
    void doesNotExistsByTitleTest() {
        //given
        String title = "상의";

        //when
        boolean existsTitle = menuRepository.existsByTitle(title);

        //then
        assertFalse(existsTitle);
    }

    @Test
    @DisplayName("메뉴에 이미 링크가 존재할 때 true 반환")
    void existsByLinkTest() {
        //given
        String link = "/top";
        Menu menu = Menu.builder()
            .title("상의")
            .link(link)
            .build();

        menuRepository.save(menu);

        //when
        boolean existsLink = menuRepository.existsByLink(link);

        //then
        assertTrue(existsLink);
    }
}
