package com.bnpp.katas.developmentbooks.service.market;

import com.bnpp.katas.developmentbooks.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MarketRule {
    BigDecimal calculateDiscount(Map<Long, Integer> itemsMap, List<Book> books);
}
