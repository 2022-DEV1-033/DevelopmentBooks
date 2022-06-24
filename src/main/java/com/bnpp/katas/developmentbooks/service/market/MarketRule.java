package com.bnpp.katas.developmentbooks.service.market;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface MarketRule {
    BigDecimal calculateDiscount(List<BookItemDto> items);
}
