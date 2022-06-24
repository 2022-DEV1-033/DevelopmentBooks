package com.bnpp.katas.developmentbooks;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.exception.BookNotFoundException;
import com.bnpp.katas.developmentbooks.exception.EmptyListDiscountCalculationException;
import com.bnpp.katas.developmentbooks.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DevelopmentBooksOrderTests {

	@Autowired
	private OrderService orderService;

	@Test
	void assertNullList() {
		Assertions.assertThrows(EmptyListDiscountCalculationException.class, () -> {
			orderService.calculatePrice(null);
		});
	}

	@Test
	void assertEmptyList() {
		Assertions.assertThrows(EmptyListDiscountCalculationException.class, () -> {
			orderService.calculatePrice(new ArrayList<>());
		});
	}

	@Test
	void assertNotFound() {
		List<BookItemDto> items = Arrays.asList(
				new BookItemDto(12, 5)
		);

		Assertions.assertThrows(BookNotFoundException.class, () -> {
			orderService.calculatePrice(items);
		});
	}

	@Test
	void calculatePrice() {
		List<BookItemDto> items = Arrays.asList(
				new BookItemDto(1, 5),
				new BookItemDto(2, 1)
				);

		Assertions.assertEquals(new BigDecimal("300.00"), orderService.calculatePrice(items));
	}
}
