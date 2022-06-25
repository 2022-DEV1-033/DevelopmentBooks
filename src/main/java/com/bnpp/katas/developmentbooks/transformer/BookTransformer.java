package com.bnpp.katas.developmentbooks.transformer;

import com.bnpp.katas.developmentbooks.dto.BookDto;
import com.bnpp.katas.developmentbooks.model.Book;

public class BookTransformer {
    private BookTransformer(){}

    public static BookDto toDto(Book input){
        BookDto output = new BookDto();
        output.setId(input.getId());
        output.setPrice(input.getPrice());
        output.setTitle(input.getTitle());

        return output;
    }
}
