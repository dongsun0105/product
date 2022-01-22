package com.musinsa.product.dto.common;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class CommonPage<T> {
    private int currentPage;
    private int maxPage;
    private int pageSize;
    private long maxItemCount;
    private List<T> content;

    public CommonPage(Page<T> page) {
        this.currentPage = page.getNumber();
        this.maxPage = page.getTotalPages();
        this.pageSize = page.getSize();
        this.maxItemCount = page.getTotalElements();
        this.content = page.getContent();
    }
}
