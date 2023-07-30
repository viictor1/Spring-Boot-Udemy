package br.com.erudio.restwithspringbootandjavaerudio.UnitTests.mapper.mocks;
import br.com.erudio.restwithspringbootandjavaerudio.dto.BookDto;
import br.com.erudio.restwithspringbootandjavaerudio.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDto mockVO() {
        return mockDto(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDto> mockVOList() {
        List<BookDto> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDto(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author " + number);
        book.setLaunchDate(new Date());
        book.setPrice(number.doubleValue());
        book.setTitle("Title " + number);
        return book;
    }

    public BookDto mockDto(Integer number) {
        BookDto book = new BookDto();
        book.setId(number.longValue());
        book.setAuthor("Author " + number);
        book.setLaunchDate(new Date());
        book.setPrice(number.doubleValue());
        book.setTitle("Title " + number);
        return book;
    }

}
