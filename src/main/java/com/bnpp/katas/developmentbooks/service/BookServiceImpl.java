package com.bnpp.katas.developmentbooks.service;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.exception.BookNotFoundException;
import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.repo.BookRepository;
import com.bnpp.katas.developmentbooks.transformer.BookTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<Book> getByIds(Set<Long> ids) {
        List<Book> books = repository.findAllById(ids);
        if(books.size() != ids.size())
            throw new BookNotFoundException();
        return books;
    }

    @Override
    public List<BookDto> getAvailableBookDtos() {
        return getAvailableBooks().stream().map(BookTransformer::toDto).collect(Collectors.toList());
    }
}
