package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisalento.se.saw.domain.Lezione;

public interface LezioneRepository extends JpaRepository<Lezione, Integer> {

}
