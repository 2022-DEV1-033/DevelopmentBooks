package com.bnpp.katas.developmentbooks.controller;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.dto.PriceResponseDto;
import com.bnpp.katas.developmentbooks.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService service;

    @PostMapping("/checkPrice")
    public PriceResponseDto checkPrice(@RequestBody List<BookItemDto> items){
        return service.checkPrice(items);
    }
}
