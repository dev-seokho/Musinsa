package com.musinsa.menu.biz.menu.dto.response;

import lombok.Builder;

@Builder
public record SubMenuResponse(
    Long id,
    Long menuId,
    String title
) {

}
