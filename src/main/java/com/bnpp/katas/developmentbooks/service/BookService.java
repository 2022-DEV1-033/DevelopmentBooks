package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAvailableBooks();
}
