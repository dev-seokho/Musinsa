package com.musinsa.menu.biz.menu.controller;

import com.musinsa.menu.biz.menu.dto.request.MenuRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateBannerRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.MenuResponse;
import com.musinsa.menu.biz.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(
        @RequestBody @Valid MenuRequest menuRequest
    ) {
        MenuResponse menuResponse = menuService.createMenu(menuRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuResponse);
    }

    @PatchMapping("/{menuId}")
    public ResponseEntity<MenuResponse> updateMenu(
        @RequestBody @Valid UpdateMenuRequest updateMenuRequest,
        @PathVariable Long menuId
    ) {
        MenuResponse menuResponse = menuService.updateMenu(updateMenuRequest, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(menuResponse);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<HttpStatus> deleteMenu(
        @PathVariable Long menuId
    ) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{menuId}/banner")
    public ResponseEntity<MenuResponse> updateBanner(
        @RequestBody @Valid UpdateBannerRequest updateBannerRequest,
        @PathVariable Long menuId
    ) {
        MenuResponse menuResponse = menuService.updateBanner(updateBannerRequest, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(menuResponse);
    }
}
