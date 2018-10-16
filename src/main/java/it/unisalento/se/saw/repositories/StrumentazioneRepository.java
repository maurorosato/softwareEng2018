package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Strumentazione;

public interface StrumentazioneRepository extends JpaRepository<Strumentazione, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Strumentazione SET stato = :statoStrumentazione  WHERE idstrumentazione = :idStrumentazione")
	void aggiornaStrumentazione(@Param("idStrumentazione") Integer id, @Param("statoStrumentazione") String stato );
	
	@Modifying
	@Transactional
	@Query("DELETE from Strumentazione WHERE idstrumentazione = :idStrumentazione")
	void rimuoviStrumentazione(@Param("idStrumentazione") Integer id);	

	@Query("SELECT COUNT(idstrumentazione) FROM Strumentazione WHERE aula_idaula = :idAula AND descrizione = :descrizione")
	public int getValidateStrumentazioneOfSegnalazione(@Param("descrizione") String descrizione,@Param("idAula") Integer idAula);

}
