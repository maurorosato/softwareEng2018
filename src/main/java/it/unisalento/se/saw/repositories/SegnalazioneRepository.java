package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Segnalazione;

public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Integer>{
	
	@Modifying
	@Transactional
<<<<<<< HEAD
	@Query("UPDATE Segnalazione SET statoSegnalazione = :statoSegnalazione WHERE idSegnalazione = :idSegnalazione")
	void cambiaStatoSegnalazione(@Param("idSegnalazione") Integer id, @Param("statoSegnalazione") String statoSegnalazione );
=======
	@Query("UPDATE Segnalazione SET statoSegnalazione = :sSegnalazione WHERE idsegnalazione = :idSegnalazione")
	void cambiaStatoSegnalazione(@Param("idSegnalazione") Integer id, @Param("sSegnalazione") String statoSegnalazione );
>>>>>>> d6a6dddd75bd759d8aeb54d078063d0fcc04c7ce
	
}
