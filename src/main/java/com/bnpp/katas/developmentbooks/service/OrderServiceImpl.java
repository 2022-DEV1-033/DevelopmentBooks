package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.service.market.MarketRule;
import com.bnpp.katas.developmentbooks.service.market.MarketRuleHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final MarketRuleHandler marketRuleHandler;

    public OrderServiceImpl(MarketRuleHandler marketRuleHandler) {
        this.marketRuleHandler = marketRuleHandler;
    }

    @Override
    public BigDecimal calculatePrice(List<BookItemDto> items) {
        MarketRule marketRule = marketRuleHandler.handle();

        if(Objects.isNull(marketRule)){
            log.info("No Handler founded.");
            return BigDecimal.ZERO;
        }else{
            return marketRule.calculateDiscount(items);
        }
    }
}
