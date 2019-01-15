package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;



public interface AppelloEsameRepository extends JpaRepository<AppelloEsame, Integer>{
	@Query("SELECT a FROM AppelloEsame a, Evento e, Insegnamento i WHERE (a.evento = e.idevento) AND (e.insegnamento =i.idinsegnamento) AND (i.corsoDiStudioIdcorsoDiStudio = :idCorsoStudente) AND (i.anno = :annoCorso)")
	public List<AppelloEsame> getAllStudente(@Param("idCorsoStudente") int idCorsoStudente, @Param("annoCorso") int annoCorso);

	@Query("SELECT a FROM AppelloEsame a, Evento e WHERE (a.evento = e.idevento) AND e. insegnamento = :insegnamento")
	public List<AppelloEsame> getAppelliEsameInsegnamento(@Param("insegnamento") Insegnamento insegnamento);
}
