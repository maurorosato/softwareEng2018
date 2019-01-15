package it.unisalento.se.saw.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import it.unisalento.se.saw.domain.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE Aula SET stato = :stato, capienza = :capienza  WHERE idaula = :idaula")
	void aggiornaAula(@Param("idaula") Integer id, @Param("stato") String stato, @Param("capienza") int capienza );
	
	@Modifying
	@Transactional
	@Query("UPDATE Aula SET wifi = :wifi WHERE idaula = :idaula")
	void aggiornaStatoAula(@Param("idaula") Integer id, @Param("wifi") byte wifi);
	
	@Modifying
	@Transactional
	@Query("UPDATE Aula SET latitudine = :latitudine, longitudine = :longitudine  WHERE idaula = :idaula")
	void localizzaAula(@Param("idaula") Integer id, @Param("latitudine") double latitudine,@Param("longitudine") double longitudine);
	
	@Modifying
	@Transactional
	@Query("DELETE from Aula WHERE idaula = :idAula")
	void rimuoviAula(@Param("idAula") Integer id);

	@Query("SELECT a FROM Aula a WHERE a.nome NOT IN ('magazzino','default') AND a.idaula NOT IN (SELECT e.aula FROM Evento e, Prenotazione p WHERE p.idprenotazione = e.prenotazione AND (p.dataInizio >= :dataInizio AND p.dataFine <= :dataFine) OR (p.dataInizio < :dataInizio AND p.dataFine > :dataInizio) OR (p.dataInizio < :dataFine AND p.dataFine > :dataFine) OR (p.dataInizio <= :dataInizio AND p.dataFine >= :dataFine))")
	public List<Aula> getAuleLibere(@Param("dataInizio") Date dataInizio, @Param("dataFine") Date dataFine);

}
