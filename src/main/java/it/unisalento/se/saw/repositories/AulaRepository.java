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
	@Query("UPDATE Aula SET stato = :statoAula, capienza = :capienzaAula  WHERE idaula = :idAula")
	void aggiornaAula(@Param("idAula") Integer id, @Param("statoAula") String stato, @Param("capienzaAula") int capienza );
}
