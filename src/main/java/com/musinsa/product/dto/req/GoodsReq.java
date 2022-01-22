package com.musinsa.product.dto.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class GoodsReq {
    @NotEmpty
    private String goodsNm;
    @NotEmpty
    private String goodsCont;
    @NotEmpty
    private String comId;
}
