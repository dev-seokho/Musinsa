package com.musinsa.menu.biz.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.menu.biz.menu.dto.request.CreateMenuRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateBannerRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateMenuRequest;
import com.musinsa.menu.biz.menu.service.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MenuController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class MenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    MenuController menuController;

    @MockBean
    MenuService menuService;

    @Test
    @DisplayName("메뉴 등록 성공")
    void createMenuSuccessTest() throws Exception {
        //given
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title("상의")
            .link("/top")
            .build();

        String content = objectMapper.writeValueAsString(createMenuRequest);

        //when
        mockMvc.perform(post("/menus")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 타이틀이 공백일 때")
    void menuCreationFailureWhenTitleIsBlankTest() throws Exception {
        //given
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title(" ")
            .link("/top")
            .build();

        String content = objectMapper.writeValueAsString(createMenuRequest);

        //when
        mockMvc.perform(post("/menus")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 링크가 공백일 때")
    void menuCreationFailureWhenLinkIsBlankTest() throws Exception {
        //given
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title("상의")
            .link(" ")
            .build();

        String content = objectMapper.writeValueAsString(createMenuRequest);

        //when
        mockMvc.perform(post("/menus")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("메뉴 등록 실패 - 링크 형식이 잘못됐을 때")
    void menuCreationFailureWhenLinkIsInvalidTest() throws Exception {
        //given
        CreateMenuRequest createMenuRequest = CreateMenuRequest.builder()
            .title("상의")
            .link("top")
            .build();

        String content = objectMapper.writeValueAsString(createMenuRequest);

        //when
        mockMvc.perform(post("/menus")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("단일 메뉴 조회 성공")
    void getMenuSuccessTest() throws Exception {
        //given
        Long menuId = 1L;

        //when
        mockMvc.perform(get("/menus/" + menuId))
            //then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("메뉴 수정 성공")
    void updateMenuSuccessTest() throws Exception {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title("상의")
            .link("/top")
            .build();

        String content = objectMapper.writeValueAsString(updateMenuRequest);

        //when
        mockMvc.perform(patch("/menus/" + menuId)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("메뉴 수정 실패 - 타이틀이 공백일 때")
    void menuUpdateFailureWhenTitleIsBlankTest() throws Exception {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title(" ")
            .link("/top")
            .build();

        String content = objectMapper.writeValueAsString(updateMenuRequest);

        //when
        mockMvc.perform(patch("/menus/" + menuId)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("메뉴 수정 실패 - 링크가 공백일 때")
    void menuUpdateFailureWhenLinkIsBlankTest() throws Exception {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title("상의")
            .link(" ")
            .build();

        String content = objectMapper.writeValueAsString(updateMenuRequest);

        //when
        mockMvc.perform(patch("/menus/" + menuId)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("메뉴 수정 실패 - 링크형식이 잘못됐을 때")
    void menuUpdateFailureWhenLinkIsInvalidTest() throws Exception {
        //given
        Long menuId = 1L;
        UpdateMenuRequest updateMenuRequest = UpdateMenuRequest.builder()
            .title("상의")
            .link("top")
            .build();

        String content = objectMapper.writeValueAsString(updateMenuRequest);

        //when
        mockMvc.perform(patch("/menus/" + menuId)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("메뉴 삭제 성공")
    void deleteMenuSuccessTest() throws Exception {
        //given
        Long menuId = 1L;

        //when
        mockMvc.perform(delete("/menus/" + menuId))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("배너 추가 성공")
    void updateBannerSuccessTest() throws Exception {
        //given
        String banner = "https://image.msscdn.net/mfile_s01/_brand/free_medium/musinsastandard.png";
        Long menuId = 1L;
        UpdateBannerRequest updateBannerRequest = UpdateBannerRequest.builder()
            .bannerImageUrl(banner)
            .build();

        String content = objectMapper.writeValueAsString(updateBannerRequest);

        //when
        mockMvc.perform(patch("/menus/" + menuId + "/banner")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("배너 추가 실패 - 배너가 공백일 때")
    void bannerUpdateFailureWhenBannerImageUrlIsBlankTest() throws Exception {
        //given
        Long menuId = 1L;
        UpdateBannerRequest updateBannerRequest = UpdateBannerRequest.builder()
            .bannerImageUrl(" ")
            .build();

        String content = objectMapper.writeValueAsString(updateBannerRequest);

        //when
        mockMvc.perform(patch("/menus/" + menuId + "/banner")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }
}
