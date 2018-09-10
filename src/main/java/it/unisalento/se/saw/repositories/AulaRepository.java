package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Utente;

public interface AulaRepository extends JpaRepository<Aula, Integer>{

}
