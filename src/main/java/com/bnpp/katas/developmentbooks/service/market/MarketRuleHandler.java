package com.bnpp.katas.developmentbooks.service.market;

import org.springframework.stereotype.Component;

@Component
public class MarketRuleHandler {
    private final BNPPMarketRule bnppMarketRule;

    public MarketRuleHandler(BNPPMarketRule bnppMarketRule) {
        this.bnppMarketRule = bnppMarketRule;
    }

    //responsible for choosing current active market rule. would be extended
    public MarketRule handle(){
        return bnppMarketRule;
    }
}
