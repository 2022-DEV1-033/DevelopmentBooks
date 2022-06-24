package com.bnpp.katas.developmentbooks;

import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DevelopmentBooksTests {

	@Autowired
	private BookService bookService;

	@Test
	void loadBooks() {
		List<Book> availableBooks = bookService.getAvailableBooks();
		Assertions.assertNotNull(availableBooks);
		Assertions.assertEquals(5, bookService.getAvailableBooks().size());
	}

}
