package com.musinsa.product.dto.res;

import com.musinsa.product.entity.Goods;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GoodsRes {
    private Long goodsNo;
    private String goodsNm;
    private String goodsCont;
    private String comId;
    private LocalDateTime regDm;
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
