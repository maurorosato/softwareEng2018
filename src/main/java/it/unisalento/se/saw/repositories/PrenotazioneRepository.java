package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unisalento.se.saw.domain.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

}
