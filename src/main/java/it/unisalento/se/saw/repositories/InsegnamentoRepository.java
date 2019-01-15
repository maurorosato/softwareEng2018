package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;

public interface InsegnamentoRepository extends JpaRepository<Insegnamento, Integer>{

	@Query("SELECT i FROM Insegnamento i WHERE i.corsoDiStudioIdcorsoDiStudio = :idCorso")
	public List<Insegnamento> getAllInsegnamentiCorso(@Param("idCorso") int idCorso);
	
	@Query("SELECT i FROM Insegnamento i WHERE i.docente = :docente")
	public List<Insegnamento> getAllInsegnamentiDocente(@Param("docente") Docente docente);
	
	@Modifying
	@Transactional
	@Query("UPDATE Insegnamento SET cfu = :cfu WHERE idInsegnamento = :idInsegnamento")
	void aggiornaInsegnamento(@Param("cfu") Integer cfu, @Param("idInsegnamento") int idInsegnamento );

	@Modifying
	@Transactional
	@Query("DELETE from Insegnamento WHERE idInsegnamento = :idInsegnamento")
	void rimuoviInsegnamento(@Param("idInsegnamento") Integer id);
}
