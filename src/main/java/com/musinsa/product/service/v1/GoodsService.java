package com.musinsa.product.service.v1;

import com.musinsa.product.constants.PageConstants;
import com.musinsa.product.dto.common.CommonPage;
import com.musinsa.product.dto.req.GoodsReq;
import com.musinsa.product.dto.res.GoodsRes;
import com.musinsa.product.entity.Goods;
import com.musinsa.product.exception.ExceptionCode;
import com.musinsa.product.exception.GoodsException;
import com.musinsa.product.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public CommonPage<GoodsRes> getGoodsList(Pageable pageable) throws Exception {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                PageConstants.PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "goodsNo"));

        Page<GoodsRes> goodsPage = goodsRepository.findAll(pageRequest).map(goods -> new GoodsRes(goods));

        return new CommonPage<>(goodsPage);
    }

    public GoodsRes getGoods(Integer goodsNo) throws Exception {
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

    public void updateGoods(Integer goodsNo, GoodsReq goodsReq) throws Exception {
        Goods goods = selectGoods(goodsNo);
        goods.updateGoods(goodsReq.getGoodsNm(), goodsReq.getGoodsCont(), goodsReq.getComId());

        goodsRepository.save(goods);
    }

    public void deleteGoods(Integer goodsNo) throws Exception {
        Goods goods = selectGoods(goodsNo);

        goodsRepository.delete(goods);
    }

    private Goods selectGoods(Integer goodsNo) throws Exception {
        return goodsRepository.findById(goodsNo)
                .orElseThrow(() -> new GoodsException(ExceptionCode.PRD001));
    }
}
