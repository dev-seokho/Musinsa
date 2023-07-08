package com.musinsa.menu.biz.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.menu.biz.menu.dto.request.SubMenuRequest;
import com.musinsa.menu.biz.menu.service.SubMenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SubMenuController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class SubMenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    SubMenuController subMenuController;

    @MockBean
    SubMenuService subMenuService;

    @Test
    @DisplayName("서브 메뉴 등록 성공")
    void createSubMenuSuccessTest() throws Exception {
        //given
        Long menuId = 1L;
        SubMenuRequest subMenuRequest = SubMenuRequest.builder()
            .title("니트")
            .build();

        String content = objectMapper.writeValueAsString(subMenuRequest);

        //when
        mockMvc.perform(post("/menus/" + menuId +"/sub-menus")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isCreated());
    }
}
