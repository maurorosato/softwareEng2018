package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisalento.se.saw.domain.Strumentazione;

public interface StrumentazioneRepository extends JpaRepository<Strumentazione, Integer> {

}
