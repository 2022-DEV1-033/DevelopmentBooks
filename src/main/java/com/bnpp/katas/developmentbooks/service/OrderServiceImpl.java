package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.dto.PriceResponseDto;
import com.bnpp.katas.developmentbooks.exception.BadInputException;
import com.bnpp.katas.developmentbooks.exception.EmptyListDiscountCalculationException;
import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.service.market.MarketRule;
import com.bnpp.katas.developmentbooks.service.market.MarketRuleHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final MarketRuleHandler marketRuleHandler;
    private final BookService bookService;

    public OrderServiceImpl(MarketRuleHandler marketRuleHandler, BookService bookService) {
        this.marketRuleHandler = marketRuleHandler;
        this.bookService = bookService;
    }

    @Override
    public PriceResponseDto checkPrice(List<BookItemDto> items) {
        return new PriceResponseDto(calculatePrice(items));
    }

    @Override
    public BigDecimal calculatePrice(List<BookItemDto> items) {
        validate(items);

        List<Book> books = bookService.getByIds(items.stream().map(BookItemDto::getId).collect(Collectors.toSet()));
        Map<Long, Integer> itemsMap = items.stream().collect(Collectors.toMap(BookItemDto::getId, BookItemDto::getQuantity));

        return sumPrice(books, itemsMap).subtract(getDiscount(itemsMap, books));
    }

    private void validate(List<BookItemDto> items) {
        if(Objects.isNull(items) || items.isEmpty())
            throw new EmptyListDiscountCalculationException();

        if(items.stream().anyMatch(item -> item.getQuantity() == 0) ||
                items.stream().map(BookItemDto::getId).distinct().count() != items.size())
            throw new BadInputException();
    }

    private BigDecimal sumPrice(List<Book> books, Map<Long, Integer> itemsMap){
        return books.stream().map(book -> book.getPrice().multiply(new BigDecimal(itemsMap.get(book.getId()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getDiscount(Map<Long, Integer> itemsMap, List<Book> books) {
        MarketRule marketRule = marketRuleHandler.handle();

        if(Objects.isNull(marketRule)){
            log.info("No Handler founded.");
            return BigDecimal.ZERO;
        }else{
            return marketRule.calculateDiscount(itemsMap, books);
        }
    }
}
