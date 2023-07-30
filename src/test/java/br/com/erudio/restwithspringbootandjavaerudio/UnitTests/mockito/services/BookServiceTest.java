package br.com.erudio.restwithspringbootandjavaerudio.UnitTests.mockito.services;

import br.com.erudio.restwithspringbootandjavaerudio.dto.BookDto;
import br.com.erudio.restwithspringbootandjavaerudio.exception.RequiredObjectIsNullException;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.Mapper;
import br.com.erudio.restwithspringbootandjavaerudio.model.Book;
import br.com.erudio.restwithspringbootandjavaerudio.repository.BookRepository;
import br.com.erudio.restwithspringbootandjavaerudio.service.BookService;
import br.com.erudio.restwithspringbootandjavaerudio.UnitTests.mapper.mocks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1l);

        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));

        var result = bookService.findById(1l);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title 1", result.getTitle());
        assertEquals("Author 1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(1, result.getPrice());
    }

    @Test
    void findAll() {
        List<Book> bookList = input.mockEntityList();

        when(bookRepository.findAll()).thenReturn(bookList);

        var result = bookService.findAll();

        var bookOne = result.get(1);

        assertNotNull(result);
        assertEquals(14, result.size());

        assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Title 1", bookOne.getTitle());
        assertEquals("Author 1", bookOne.getAuthor());
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(1, bookOne.getPrice());

        var bookFive = result.get(5);

        assertTrue(bookFive.toString().contains("links: [</api/book/v1/5>;rel=\"self\"]"));
        assertEquals("Title 5", bookFive.getTitle());
        assertEquals("Author 5", bookFive.getAuthor());
        assertNotNull(bookFive.getLaunchDate());
        assertEquals(5, bookFive.getPrice());

        var bookTen = result.get(10);

        assertTrue(bookTen.toString().contains("links: [</api/book/v1/10>;rel=\"self\"]"));
        assertEquals("Title 10", bookTen.getTitle());
        assertEquals("Author 10", bookTen.getAuthor());
        assertNotNull(bookTen.getLaunchDate());
        assertEquals(10, bookTen.getPrice());
    }

    @Test
    void create() {
        BookDto vo = input.mockDto(1);
        vo.setId(1L);

        Book entity = Mapper.parseObject(vo, Book.class);
        entity.setId(1L);

        doReturn(entity).when(bookRepository).save(entity);

        var result = bookService.create(vo);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title 1", result.getTitle());
        assertEquals("Author 1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(1, result.getPrice());
    }

    @Test
    void createWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> bookService.create(null));
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        book.setId(1l);
        Book persisted = book;

        BookDto dto = input.mockDto(1);
        dto.setId(1l);

        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));
        lenient().when(bookRepository.save(book)).thenReturn(persisted);

        var result = bookService.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Title 1", result.getTitle());
        assertEquals("Author 1", result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(1, result.getPrice());
    }

    @Test
    void updateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> bookService.update(null));
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void delete() {

    }
}
