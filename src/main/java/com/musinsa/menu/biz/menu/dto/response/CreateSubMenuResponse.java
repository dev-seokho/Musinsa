package com.musinsa.menu.biz.menu.dto.response;

import lombok.Builder;

@Builder
public record CreateSubMenuResponse(
    Long id,
    Long menuId,
    String title
) {

}
