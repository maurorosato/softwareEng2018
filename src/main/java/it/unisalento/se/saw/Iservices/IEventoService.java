package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;

public interface IEventoService {
	public List<Evento> getAll() throws EventoNotFoundException;

}
