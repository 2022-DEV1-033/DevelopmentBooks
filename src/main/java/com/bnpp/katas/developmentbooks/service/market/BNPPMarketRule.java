package com.bnpp.katas.developmentbooks.service.market;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.exception.EmptyListDiscountCalculationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
public class BNPPMarketRule implements MarketRule{

    @Override
    public BigDecimal calculateDiscount(List<BookItemDto> items) {
        if(Objects.isNull(items) || items.isEmpty())
            throw new EmptyListDiscountCalculationException();
        return BigDecimal.ZERO;
    }
}
