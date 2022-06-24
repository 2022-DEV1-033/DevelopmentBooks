package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAvailableBooks() {
        return repository.findAll();
    }
}
