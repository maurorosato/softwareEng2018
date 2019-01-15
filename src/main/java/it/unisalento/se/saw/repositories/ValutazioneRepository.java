package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Valutazione;

public interface ValutazioneRepository extends JpaRepository<Valutazione, Integer>{
	@Query("SELECT v FROM Valutazione v WHERE v.lezione = :lezione AND v.studente = :studente")
	public List<Valutazione> getValutazioneStudenteLezione(@Param("studente") Studente studente,@Param("lezione") Lezione lezione);
	
	@Query("SELECT v FROM Valutazione v WHERE v.lezione = :lezione")
	public List<Valutazione> getValutazioneLezione(@Param("lezione") Lezione lezione);
	
	@Query("SELECT v FROM Valutazione v WHERE v.materialeDidatticoIdmaterialeDidattico = :idMatDidattico AND v.studente = :studente")
	public List<Valutazione> getValutazioneStudenteMatDidattico(@Param("studente") Studente studente,@Param("idMatDidattico") int idMatDidattico);

	@Query("SELECT v FROM Valutazione v WHERE v.materialeDidatticoIdmaterialeDidattico = :idMatDidattico")
	public List<Valutazione> getValutazioneMatDidattico(@Param("idMatDidattico") int idMatDidattico);

}
