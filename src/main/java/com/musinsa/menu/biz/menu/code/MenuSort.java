package com.musinsa.menu.biz.menu.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum MenuSort {

    /**
     * Todo : 추후 메뉴 OrderBy 기준이 더 생기면 추가해줍니다.
     */
    CREATED_AT("createdAt", Sort.by("createdAt").descending());

    private final String orderBy;
    private final Sort sort;

    public static Sort findSortCondition(String orderBy) {
        for (MenuSort menuSort : MenuSort.values()) {
            if (menuSort.orderBy.equals(orderBy)) {
                return menuSort.getSort();
            }
        }
        return CREATED_AT.getSort();
    }
}
