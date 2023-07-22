package br.com.erudio.restwithspringbootandjavaerudio.repository;

import br.com.erudio.restwithspringbootandjavaerudio.model.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonDto, Long> { }
