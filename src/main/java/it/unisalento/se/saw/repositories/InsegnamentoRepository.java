package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Insegnamento;

public interface InsegnamentoRepository extends JpaRepository<Insegnamento, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE Insegnamento SET cfu = :cfu WHERE idInsegnamento = :idInsegnamento")
	void aggiornaInsegnamento(@Param("cfu") Integer cfu, @Param("idInsegnamento") int idInsegnamento );

}
