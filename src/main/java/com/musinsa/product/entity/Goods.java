package com.musinsa.product.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "GOODS_SEQ_GENERATOR",
        sequenceName = "GOODS_SEQ",
        allocationSize = 1)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GOODS_SEQ_GENERATOR")
    private Integer goodsNo;
    private String goodsNm;
    @Lob
    private String goodsCont;
    private String comId;
    private LocalDateTime regDm;
    private LocalDateTime updDm;

    @Builder
    public Goods(String goodsNm, String goodsCont, String comId) {
        this.goodsNm = goodsNm;
        this.goodsCont = goodsCont;
        this.comId = comId;
        this.regDm = this.updDm = LocalDateTime.now();
    }

    public void updateGoods(String goodsNm, String goodsCont, String comId) {
        this.goodsNm = goodsNm;
        this.goodsCont = goodsCont;
        this.comId = comId;
        this.updDm = LocalDateTime.now();
    }

}
