package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.model.Book;

import java.util.List;
import java.util.Set;

public interface BookService {
    List<Book> getAvailableBooks();
    List<Book> getByIds(Set<Long> ids);
    List<BookDto> getAvailableBookDtos();
}
