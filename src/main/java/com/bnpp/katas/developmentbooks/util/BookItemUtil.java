package com.bnpp.katas.developmentbooks.util;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookItemUtil {
    private BookItemUtil(){}

    public static Map<Long, Integer> toMap(List<BookItemDto> items) {
        return items.stream().collect(Collectors.toMap(BookItemDto::getId, BookItemDto::getQuantity));
    }
}
