package com.musinsa.product.dto.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.musinsa.product.entity.Goods;
import com.musinsa.product.serializer.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GoodsRes {
    private Integer goodsNo;
    private String goodsNm;
    private String goodsCont;
    private String comId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime regDm;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updDm;

    public GoodsRes(Goods goods) {
        this.goodsNo = goods.getGoodsNo();
        this.goodsNm = goods.getGoodsNm();
        this.goodsCont = goods.getGoodsCont();
        this.comId = goods.getComId();
        this.regDm = goods.getRegDm();
        this.updDm = goods.getUpdDm();
    }
}
