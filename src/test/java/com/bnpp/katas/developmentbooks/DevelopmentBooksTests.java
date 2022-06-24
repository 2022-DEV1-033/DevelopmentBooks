package com.bnpp.katas.developmentbooks;

import com.bnpp.katas.developmentbooks.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DevelopmentBooksTests {

	@Autowired
	private BookService bookService;

	@Test
	void loadBooks() {
	}

}
