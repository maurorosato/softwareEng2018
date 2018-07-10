package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;

public interface IAulaService {
	public List<Aula> getAll() throws AulaNotFoundException;
}
