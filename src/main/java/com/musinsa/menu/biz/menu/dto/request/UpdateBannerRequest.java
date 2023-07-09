package com.musinsa.menu.biz.menu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateBannerRequest(
    @NotBlank
    String bannerImageUrl
) {

}
