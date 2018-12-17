package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;

public interface MaterialeDidatticoRepository extends JpaRepository<MaterialeDidattico, Integer> {
	@Query("SELECT m FROM MaterialeDidattico m WHERE m.lezione = :lezione")
	public List<MaterialeDidattico> getAllLezione(@Param("lezione") Lezione lezione);
}
