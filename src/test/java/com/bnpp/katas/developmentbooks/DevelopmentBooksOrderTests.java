package com.bnpp.katas.developmentbooks;

import com.bnpp.katas.developmentbooks.model.Book;
import com.bnpp.katas.developmentbooks.service.BookService;
import com.bnpp.katas.developmentbooks.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DevelopmentBooksOrderTests {

	@Autowired
	private OrderService orderService;

	@Test
	void test() {
		orderService.calculatePrice();
	}
}
