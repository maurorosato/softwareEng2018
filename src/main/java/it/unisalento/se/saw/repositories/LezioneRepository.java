package it.unisalento.se.saw.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;

public interface LezioneRepository extends JpaRepository<Lezione, Integer> {

	@Query("SELECT l FROM Lezione l, Evento e, Insegnamento i WHERE (l.evento = e.idevento) AND (e.insegnamento =i.idinsegnamento) AND (i.corsoDiStudioIdcorsoDiStudio = :idCorsoStudente) AND (i.anno = :annoCorso)")
	public List<Lezione> getAllStudente(@Param("idCorsoStudente") int idCorsoStudente, @Param("annoCorso") int annoCorso);

	@Query("SELECT l FROM Lezione l, Evento e WHERE (l.evento = e.idevento) AND e. insegnamento = :insegnamento")
	public List<Lezione> getLezioniInsegnamento(@Param("insegnamento") Insegnamento insegnamento);

}
