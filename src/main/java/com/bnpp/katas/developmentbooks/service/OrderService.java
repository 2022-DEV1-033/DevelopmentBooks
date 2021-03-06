package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.dto.PriceResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    BigDecimal calculatePrice(List<BookItemDto> items);
    PriceResponseDto checkPrice(List<BookItemDto> items);
}
