package com.bnpp.katas.developmentbooks.service.market;

import com.bnpp.katas.developmentbooks.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BNPPMarketRule implements MarketRule{

    @Value("#{${app.market.bnpp.discounts}}")
    private HashMap<Integer,Integer> bnppDiscounts;

    @Override
    public BigDecimal calculateDiscount(Map<Long, Integer> itemsMap, List<Book> books) {
        Map<Long, Integer> itemsMapToCalculate = new HashMap<>();
        itemsMapToCalculate.putAll(itemsMap);
        Integer maxPackSize = bnppDiscounts.entrySet().stream().map(Map.Entry::getKey).max(Integer::compareTo).get();
        Map<Long, Book> booksMap = books.stream().collect(Collectors.toMap(Book::getId, book -> book));
        BigDecimal discount = BigDecimal.ZERO;

        while (itemsMapToCalculate.size() > 0) {
            int packSize = 0;
            BigDecimal packPrice = BigDecimal.ZERO;

            for (Iterator<Map.Entry<Long, Integer>> iterator = itemsMapToCalculate.entrySet().iterator();
                 iterator.hasNext() && packSize < maxPackSize; ) {
                Map.Entry<Long, Integer> entry = iterator.next();
                if(entry.getValue() > 0) {
                    packPrice = packPrice.add(booksMap.get(entry.getKey()).getPrice());
                    packSize++;
                    entry.setValue(entry.getValue() - 1);
                }

                if(entry.getValue() == 0) {
                    iterator.remove();
                }
            }

            if(bnppDiscounts.containsKey(packSize)){
                BigDecimal packDiscount = packPrice.multiply(new BigDecimal(bnppDiscounts.get(packSize)))
                        .divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
                discount = discount.add(packDiscount);
            }
        }

        return discount;
    }
}
