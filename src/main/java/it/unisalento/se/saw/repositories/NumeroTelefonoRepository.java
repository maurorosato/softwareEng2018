package it.unisalento.se.saw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unisalento.se.saw.domain.NumeroTelefono;

public interface NumeroTelefonoRepository extends JpaRepository<NumeroTelefono, Integer> {

}
