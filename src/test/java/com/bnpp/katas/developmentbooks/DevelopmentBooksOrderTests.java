package com.bnpp.katas.developmentbooks;

import com.bnpp.katas.developmentbooks.dto.BookItemDto;
import com.bnpp.katas.developmentbooks.exception.BadInputException;
import com.bnpp.katas.developmentbooks.exception.BookNotFoundException;
import com.bnpp.katas.developmentbooks.exception.EmptyListDiscountCalculationException;
import com.bnpp.katas.developmentbooks.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
	void assetZeroQuantity() {
		List<BookItemDto> items = Arrays.asList(
				new BookItemDto(3, 0)
		);

		Assertions.assertThrows(BadInputException.class, () -> {
			orderService.calculatePrice(items);
		});
	}

	@Test
	void assetDuplicateId() {
		List<BookItemDto> items = Arrays.asList(
				new BookItemDto(3, 2),
				new BookItemDto(3, 3)
		);

		Assertions.assertThrows(BadInputException.class, () -> {
			orderService.calculatePrice(items);
		});
	}

	@Test
	void calculatePriceWithoutDiscount() {
		List<BookItemDto> items = Arrays.asList(new BookItemDto(1, 5));

		Assertions.assertEquals(new BigDecimal("250.00"), orderService.calculatePrice(items));
	}

	@Test
	void calculatePriceOfMultiPack() {
		List<BookItemDto> items = Arrays.asList(
				new BookItemDto(1, 5),
				new BookItemDto(2, 3),
				new BookItemDto(3, 2),
				new BookItemDto(4, 4),
				new BookItemDto(5, 1)
		);

		Assertions.assertEquals(new BigDecimal("627.50"), orderService.calculatePrice(items));
	}

	@Test
	@Disabled("Disabled until get answer from client!")
	void calculatePriceOfDuplicateMultiPack() {
		List<BookItemDto> items = Arrays.asList(
				new BookItemDto(1, 2),
				new BookItemDto(2, 2),
				new BookItemDto(4, 2),
				new BookItemDto(3, 1),
				new BookItemDto(5, 1)
		);

		Assertions.assertEquals(new BigDecimal("320.00"), orderService.calculatePrice(items));
	}
}
