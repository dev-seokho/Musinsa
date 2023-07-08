package com.musinsa.menu.biz.menu.dto.response;

import lombok.Builder;

@Builder
public record MenuResponse(
    Long id,
    String title,
    String link
) {

}
