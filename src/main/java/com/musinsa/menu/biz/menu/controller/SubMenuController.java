package com.musinsa.menu.biz.menu.controller;

import com.musinsa.menu.biz.menu.dto.request.SubMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.SubMenuResponse;
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
    public ResponseEntity<SubMenuResponse> createSubMenu(
        @RequestBody @Valid SubMenuRequest subMenuRequest,
        @PathVariable Long menuId
    ) {
        SubMenuResponse subMenuResponse = subMenuService.createSubMenu(subMenuRequest, menuId);
        return ResponseEntity.status(HttpStatus.CREATED).body(subMenuResponse);
    }
}
