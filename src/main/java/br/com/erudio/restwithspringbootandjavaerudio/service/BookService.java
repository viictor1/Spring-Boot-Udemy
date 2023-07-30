package br.com.erudio.restwithspringbootandjavaerudio.service;

import br.com.erudio.restwithspringbootandjavaerudio.controller.BookController;
import br.com.erudio.restwithspringbootandjavaerudio.dto.BookDto;
import br.com.erudio.restwithspringbootandjavaerudio.exception.RequiredObjectIsNullException;
import br.com.erudio.restwithspringbootandjavaerudio.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.Mapper;
import br.com.erudio.restwithspringbootandjavaerudio.model.Book;
import br.com.erudio.restwithspringbootandjavaerudio.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public BookDto findById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));
        BookDto dto =  Mapper.parseObject(book, BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<BookDto> findAll(){

        var books =  Mapper.parseListObjects(bookRepository.findAll(), BookDto.class);
        books
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class)
                        .findById(p.getId())).withSelfRel()));
        return books;
    }

    public BookDto create(BookDto book){
        if(book == null) throw new RequiredObjectIsNullException();
        var entity = Mapper.parseObject(book, Book.class);
        entity = bookRepository.save(entity);
        BookDto dto =  Mapper.parseObject(entity, BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public BookDto update(BookDto book){
        if(book == null) throw new RequiredObjectIsNullException();

        Book entity = bookRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        BookDto dto =  Mapper.parseObject(entity, BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public void delete(Long id){
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        bookRepository.delete(entity);
    }
}
