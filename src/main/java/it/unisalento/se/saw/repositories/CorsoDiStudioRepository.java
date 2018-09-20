package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.CorsoDiStudio;

public interface CorsoDiStudioRepository extends JpaRepository<CorsoDiStudio, Integer> {
	
	@Modifying
	@Transactional
	@Query("DELETE from CorsoDiStudio WHERE idcorsoDiStudio = :idCorso")
	void rimuoviCorsoDiStudio(@Param("idCorso") Integer id);
}

