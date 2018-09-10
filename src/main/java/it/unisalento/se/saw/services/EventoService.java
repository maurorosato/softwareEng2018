package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.repositories.EventoRepository;

@Service
public class EventoService implements IEventoService {
	
	@Autowired
	EventoRepository eventoRepository;
	
	@Override
	public List<Evento> getAll() throws EventoNotFoundException {
		return eventoRepository.findAll() ;
	}
	
	

}
