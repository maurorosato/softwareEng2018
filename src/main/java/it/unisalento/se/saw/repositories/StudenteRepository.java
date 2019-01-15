package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Integer>{
	@Modifying
	@Transactional
	@Query("UPDATE Studente SET indirizzo = :indirizzo  WHERE idStudente = :idStudente")
	void aggiornaStudente(@Param("idStudente") Integer idStudente,@Param("indirizzo") String indirizzo );
	
	@Query("SELECT s FROM Studente s, Iscrizione i WHERE s.idstudente = i.studente AND i.insegnamento = :insegnamento")
	public List<Studente> getStudenteIscrittoInsegnamento(@Param("insegnamento") Insegnamento insegnamento);

}
