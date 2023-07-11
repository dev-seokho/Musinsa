package com.musinsa.menu.biz.menu.controller;

import com.musinsa.menu.biz.menu.code.MenuSort;
import com.musinsa.menu.biz.menu.dto.request.CreateMenuRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateBannerRequest;
import com.musinsa.menu.biz.menu.dto.request.UpdateMenuRequest;
import com.musinsa.menu.biz.menu.dto.response.CreateMenuResponse;
import com.musinsa.menu.biz.menu.dto.response.MenuInfoResponse;
import com.musinsa.menu.biz.menu.dto.response.MenuResponse;
import com.musinsa.menu.biz.menu.dto.response.UpdateBannerResponse;
import com.musinsa.menu.biz.menu.dto.response.UpdateMenuResponse;
import com.musinsa.menu.biz.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<CreateMenuResponse> createMenu(
        @RequestBody @Valid CreateMenuRequest createMenuRequest
    ) {
        CreateMenuResponse createMenuResponse = menuService.createMenu(createMenuRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createMenuResponse);
    }

    @GetMapping
    public ResponseEntity<Slice<MenuResponse>> getMenus(
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "30") int size,
        @RequestParam(name = "sort", required = false) MenuSort menuSort
    ) {
        Slice<MenuResponse> menuResponses = menuService.getMenus(page, size, menuSort);
        return ResponseEntity.status(HttpStatus.OK).body(menuResponses);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuInfoResponse> getMenu(
        @PathVariable Long menuId
    ) {
        MenuInfoResponse menuInfoResponse = menuService.getMenu(menuId);
        return ResponseEntity.status(HttpStatus.OK).body(menuInfoResponse);
    }

    @PatchMapping("/{menuId}")
    public ResponseEntity<UpdateMenuResponse> updateMenu(
        @RequestBody @Valid UpdateMenuRequest updateMenuRequest,
        @PathVariable Long menuId
    ) {
        UpdateMenuResponse updateMenuResponse = menuService.updateMenu(updateMenuRequest, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(updateMenuResponse);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<HttpStatus> deleteMenu(
        @PathVariable Long menuId
    ) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{menuId}/banners")
    public ResponseEntity<UpdateBannerResponse> updateBanner(
        @RequestBody @Valid UpdateBannerRequest updateBannerRequest,
        @PathVariable Long menuId
    ) {
        UpdateBannerResponse updateBannerResponse = menuService
            .updateBanner(updateBannerRequest, menuId);
        return ResponseEntity.status(HttpStatus.OK).body(updateBannerResponse);
    }
}
