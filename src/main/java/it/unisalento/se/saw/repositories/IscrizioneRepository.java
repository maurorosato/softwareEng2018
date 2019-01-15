package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Integer>{

	@Query("SELECT i FROM Iscrizione i WHERE i.studente = :idStudente AND i.insegnamento = :idInsegnamento")
	public List<Iscrizione> getIscrizione(@Param("idInsegnamento") Insegnamento insegnamento,@Param("idStudente") Studente studente);
}
