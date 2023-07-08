package com.musinsa.menu.biz.menu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MenuRequest(
    @NotBlank(message = "메뉴 타이틀은 공백일 수 없습니다.")
    String title,
    @NotBlank(message = "메뉴 링크는 공백일 수 없습니다.")
    @Pattern(regexp = "^/[^/]+$", message = "잘못된 링크 형식입니다.")
    String link
) {

}
