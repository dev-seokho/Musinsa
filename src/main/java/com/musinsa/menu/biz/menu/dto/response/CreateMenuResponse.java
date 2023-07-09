package com.musinsa.menu.biz.menu.dto.response;

import lombok.Builder;

@Builder
public record CreateMenuResponse(
    Long id,
    String title,
    String link
) {

}
