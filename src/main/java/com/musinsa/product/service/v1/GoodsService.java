package com.musinsa.product.service.v1;

import com.musinsa.product.dto.common.CommonPage;
import com.musinsa.product.dto.req.GoodsReq;
import com.musinsa.product.dto.res.GoodsRes;
import com.musinsa.product.entity.Goods;
import com.musinsa.product.exception.ExceptionCode;
import com.musinsa.product.exception.ProductException;
import com.musinsa.product.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public CommonPage<GoodsRes> getGoodsList(Pageable pageable) throws Exception {
        Page<GoodsRes> goodsPage = goodsRepository.findAll(pageable).map(goods -> new GoodsRes(goods));
        return new CommonPage<>(goodsPage);
    }

    public GoodsRes getGoods(Long goodsNo) throws Exception {
        return new GoodsRes(selectGoods(goodsNo));
    }

    public void insertGoods(GoodsReq goodsReq) throws Exception {
        Goods goods = Goods.builder()
                .goodsNm(goodsReq.getGoodsNm())
                .goodsCont(goodsReq.getGoodsCont())
                .comId(goodsReq.getComId())
                .build();

        goodsRepository.save(goods);
    }

    public void updateGoods(Long goodsNo, GoodsReq goodsReq) throws Exception {
        Goods goods = selectGoods(goodsNo);
        goods.updateGoods(goodsReq.getGoodsNm(), goodsReq.getGoodsCont(), goodsReq.getComId());

        goodsRepository.save(goods);
    }

    public void deleteGoods(Long goodsNo) throws Exception {
        Goods goods = selectGoods(goodsNo);

        goodsRepository.delete(goods);
    }

    private Goods selectGoods(Long goodsNo) throws Exception {
        return goodsRepository.findById(goodsNo)
                .orElseThrow(() -> new ProductException(ExceptionCode.ERR001));
    }
}
