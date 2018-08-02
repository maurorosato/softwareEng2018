package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unisalento.se.saw.domain.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Integer>{

}
