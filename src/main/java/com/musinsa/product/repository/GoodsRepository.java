package com.musinsa.product.repository;

import com.musinsa.product.entity.Goods;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodsRepository extends PagingAndSortingRepository<Goods, Integer> {
}
