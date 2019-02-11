package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Utente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
	@Modifying
	@Transactional
	@Query("UPDATE Docente SET grado = :grado, stipendio = :stipendio WHERE idDocente = :idDocente")
	void aggiornaDocente( @Param("idDocente") int idDocente, @Param("grado") String grado, @Param("stipendio") float stipendio );
	
	public Docente findByUtente(Utente utente);

}
