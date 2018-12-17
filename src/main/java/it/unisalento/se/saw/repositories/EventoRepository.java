package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE Evento SET aula_idaula = :idAula WHERE idevento = :idEvento")
	void modificaEventoAuladisponibile(@Param("idEvento") Integer idEvento, @Param("idAula") Integer idAula);
}
