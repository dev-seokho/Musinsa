package com.musinsa.menu.biz.menu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateSubMenuRequest(
    @NotBlank(message = "서브 메뉴 타이틀은 공백일 수 없습니다.")
    String title
) {

}
