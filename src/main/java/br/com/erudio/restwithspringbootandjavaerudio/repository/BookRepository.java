package br.com.erudio.restwithspringbootandjavaerudio.repository;

import br.com.erudio.restwithspringbootandjavaerudio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
