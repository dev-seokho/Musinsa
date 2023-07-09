package com.musinsa.menu.biz.menu.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record MenuResponse(
    Long id,
    String title,
    String link,
    String bannerImageUrl,
    List<SubMenuResponse> subMenuResponses
) {

}
