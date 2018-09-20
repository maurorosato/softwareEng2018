package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.dto.AulaDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;

public interface IAulaService {
	public Aula save(Aula aula);
	public List<Aula> getAll() throws AulaNotFoundException;
	public Aula getById(int id) throws AulaNotFoundException;
	public void aggiornaAula(AulaDto aulaDto) throws AulaNotFoundException;
	public void rimuoviAula(int idAula) throws AulaNotFoundException;

}
