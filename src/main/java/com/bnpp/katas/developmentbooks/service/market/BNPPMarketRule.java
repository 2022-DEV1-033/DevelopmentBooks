package com.bnpp.katas.developmentbooks.service.market;

import com.bnpp.katas.developmentbooks.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BNPPMarketRule implements MarketRule{

    @Value("#{${app.market.bnpp.discounts}}")
    private HashMap<Integer,Integer> bnppDiscounts;

    private List<BigDecimal> discounts = new ArrayList<>();

    @Override
    public BigDecimal calculateDiscount(Map<Long, Integer> itemsMap, List<Book> books) {
        Integer maxPackSize = bnppDiscounts.entrySet().stream().map(Map.Entry::getKey).max(Integer::compareTo).get();
        Map<Long, Book> booksMap = books.stream().collect(Collectors.toMap(Book::getId, book -> book));

        calculateAllPackDiscounts(itemsMap, maxPackSize, booksMap);

        return getMaxDiscount();
    }

    private void calculateAllPackDiscounts(Map<Long, Integer> itemsMap, Integer maxPackSize, Map<Long, Book> booksMap) {
        BigDecimal packingDiscount = BigDecimal.ZERO;
        Map<Long, Integer> itemsMapToCalculate = cloneItems(itemsMap);
        while (itemsMapToCalculate.size() > 0) {
            packingDiscount = packingDiscount.add(calculateSinglePackDiscount(itemsMapToCalculate, maxPackSize, booksMap));
        }
        discounts.add(packingDiscount);
    }

    private BigDecimal getMaxDiscount(){
        return discounts.stream().max(BigDecimal::compareTo).get();
    }

    private BigDecimal calculateSinglePackDiscount(Map<Long, Integer> itemsMapToCalculate, Integer maxPackSize, Map<Long, Book> booksMap) {
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
            return packPrice.multiply(new BigDecimal(bnppDiscounts.get(packSize)))
                    .divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
        }

        return BigDecimal.ZERO;
    }

    private Map<Long, Integer> cloneItems(Map<Long, Integer> itemsMap) {
        Map<Long, Integer> itemsClone = new HashMap<>();
        itemsClone.putAll(itemsMap);
        return itemsClone;
    }
}
