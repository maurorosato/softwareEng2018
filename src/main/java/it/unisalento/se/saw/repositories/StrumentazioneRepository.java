package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Strumentazione;

public interface StrumentazioneRepository extends JpaRepository<Strumentazione, Integer> {
	
	@Modifying
	@Transactional
<<<<<<< HEAD
	@Query("UPDATE Strumentazione SET stato = :statoStrumentazione  WHERE idStrumentazione = :idStrumentazione")
=======
	@Query("UPDATE Strumentazione SET stato = :statoStrumentazione  WHERE idstrumentazione = :idStrumentazione")
>>>>>>> d6a6dddd75bd759d8aeb54d078063d0fcc04c7ce
	void aggiornaStrumentazione(@Param("idStrumentazione") Integer id, @Param("statoStrumentazione") String stato );
}
