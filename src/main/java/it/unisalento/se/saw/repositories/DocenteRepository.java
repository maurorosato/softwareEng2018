package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisalento.se.saw.domain.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

}