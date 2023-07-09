package com.musinsa.menu.biz.menu.dto.response;

import lombok.Builder;

@Builder
public record UpdateBannerResponse(
    Long id,
    String title,
    String link,
    String bannerImageUrl
) {

}
