package com.musinsa.menu.biz.menu.controller;

import com.musinsa.menu.biz.menu.dto.request.CreateSubMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.CreateSubMenuResponse;
import com.musinsa.menu.biz.menu.service.SubMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class SubMenuController {

    private final SubMenuService subMenuService;

    @PostMapping("/{menuId}/sub-menus")
    public ResponseEntity<CreateSubMenuResponse> createSubMenu(
        @RequestBody @Valid CreateSubMenuRequest createSubMenuRequest,
        @PathVariable Long menuId
    ) {
        CreateSubMenuResponse createSubMenuResponse = subMenuService
            .createSubMenu(createSubMenuRequest, menuId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSubMenuResponse);
    }
}
