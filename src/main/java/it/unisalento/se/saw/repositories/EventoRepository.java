package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisalento.se.saw.domain.Evento;



public interface EventoRepository extends JpaRepository<Evento, Integer>{

	
	
}
