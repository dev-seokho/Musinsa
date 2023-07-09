package com.musinsa.menu.biz.menu.dto.response;

import lombok.Builder;

@Builder
public record UpdateMenuResponse(
    Long id,
    String title,
    String link,
    String bannerImageUrl
) {

}
