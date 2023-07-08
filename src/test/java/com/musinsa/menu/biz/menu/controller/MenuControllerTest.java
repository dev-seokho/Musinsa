package com.musinsa.menu.biz.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.menu.biz.menu.dto.request.MenuRequest;
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
        MenuRequest menuRequest = MenuRequest.builder()
            .title("상의")
            .link("/top")
            .build();

        String content = objectMapper.writeValueAsString(menuRequest);

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
        MenuRequest menuRequest = MenuRequest.builder()
            .title(" ")
            .link("/top")
            .build();

        String content = objectMapper.writeValueAsString(menuRequest);

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
        MenuRequest menuRequest = MenuRequest.builder()
            .title("상의")
            .link(" ")
            .build();

        String content = objectMapper.writeValueAsString(menuRequest);

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
        MenuRequest menuRequest = MenuRequest.builder()
            .title("상의")
            .link("top")
            .build();

        String content = objectMapper.writeValueAsString(menuRequest);

        //when
        mockMvc.perform(post("/menus")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isBadRequest());
    }

}
