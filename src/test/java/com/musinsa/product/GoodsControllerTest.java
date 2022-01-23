package com.musinsa.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.product.dto.common.CommonResponseBody;
import com.musinsa.product.dto.req.GoodsReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class GoodsControllerTest {
    private static final String BASIC_URL = "/v1/goods";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    private String getResponseCode(MvcResult result) throws Exception {
        return objectMapper
                .readValue(result.getResponse().getContentAsString(), CommonResponseBody.class)
                .getCode();
    }

    @Test
    @DisplayName("상품 목록 조회")
    void get_goods_list() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASIC_URL)
                .param("page", "0"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("0", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 조회")
    void get_goods() throws Exception {
        Integer goodsNo = 3;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASIC_URL + "/" + goodsNo))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("0", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 조회 - ID 미존재 예외")
    void get_goods_invalid_id_exception() throws Exception {
        Integer goodsNo = 999;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASIC_URL + "/" + goodsNo))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("PRD001", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 등록")
    void insert_goods() throws Exception {
        GoodsReq req = new GoodsReq();
        req.setGoodsNm("테스트 상품 20");
        req.setGoodsCont("테스트 상품 20 입니다.");
        req.setComId("account20");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(BASIC_URL)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("0", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 수정")
    void update_goods() throws Exception {
        Integer goodsNo = 3;

        GoodsReq req = new GoodsReq();
        req.setGoodsNm("테스트 상품 3 수정");
        req.setGoodsCont("테스트 상품 3 수정 입니다.");
        req.setComId("account20");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put(BASIC_URL + "/" + goodsNo)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("0", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 수정 - ID 미존재 예외")
    void update_goods_invalid_id_exception() throws Exception {
        Integer goodsNo = 999;

        GoodsReq req = new GoodsReq();
        req.setGoodsNm("테스트 상품 3 수정");
        req.setGoodsCont("테스트 상품 3 수정 입니다.");
        req.setComId("account20");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put(BASIC_URL + "/" + goodsNo)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("PRD001", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 삭제")
    void delete_goods() throws Exception {
        Integer goodsNo = 3;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(BASIC_URL + "/" + goodsNo))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("0", getResponseCode(result));
    }

    @Test
    @DisplayName("상품 삭제 - ID 미존재 예외")
    void delete_goods_invalid_id_exception() throws Exception {
        Integer goodsNo = 999;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(BASIC_URL + "/" + goodsNo))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("PRD001", getResponseCode(result));
    }
}
