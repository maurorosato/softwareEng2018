package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import it.unisalento.se.saw.domain.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer>{

	@Modifying
	@Transactional
<<<<<<< HEAD
	@Query("UPDATE Aula SET stato = :stato, capienza = :capienza  WHERE idaula = :idaula")
	void aggiornaAula(@Param("idaula") Integer id, @Param("stato") String stato, @Param("capienza") int capienza );
=======
	@Query("UPDATE Aula SET stato = :statoAula, capienza = :capienzaAula  WHERE idaula = :idAula")
	void aggiornaAula(@Param("idAula") Integer id, @Param("statoAula") String stato, @Param("capienzaAula") int capienza );
>>>>>>> d6a6dddd75bd759d8aeb54d078063d0fcc04c7ce
}
