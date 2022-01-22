package com.musinsa.product.controller.v1;

import com.musinsa.product.dto.common.CommonPage;
import com.musinsa.product.dto.common.CommonResponse;
import com.musinsa.product.dto.req.GoodsReq;
import com.musinsa.product.dto.res.GoodsRes;
import com.musinsa.product.service.v1.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v1/products")
@RestController
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping
    public CommonResponse<CommonPage<GoodsRes>> getGoodsList(Pageable pageable) throws Exception {
        return new CommonResponse<>(goodsService.getGoodsList(pageable));
    }

    @GetMapping("/{goodsNo}")
    public CommonResponse<GoodsRes> getGoods(@PathVariable Long goodsNo) throws Exception {
        return new CommonResponse<>(goodsService.getGoods(goodsNo));
    }

    @PostMapping
    public CommonResponse<Void> insertGoods(@RequestBody @Valid GoodsReq goodsReq) throws Exception {
        goodsService.insertGoods(goodsReq);
        return new CommonResponse<>();
    }

    @PutMapping("/{goodsNo}")
    public CommonResponse<Void> updateGoods(@PathVariable Long goodsNo, @RequestBody @Valid GoodsReq goodsReq) throws Exception {
        goodsService.updateGoods(goodsNo, goodsReq);
        return new CommonResponse<>();
    }

    @DeleteMapping("/{goodsNo}")
    public CommonResponse<Void> deleteGoods(@PathVariable Long goodsNo) throws Exception {
        goodsService.deleteGoods(goodsNo);
        return new CommonResponse<>();
    }
}
