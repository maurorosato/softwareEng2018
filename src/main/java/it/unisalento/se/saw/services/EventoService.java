package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.dto.AulaDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
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
	
	@Transactional
	public Evento save(Evento evento) {
		// TODO Auto-generated method stub
		return eventoRepository.save(evento);
	}
	
	@Override
	public void modificaEventoAuladisponibile(int idEvento, int idAula) throws EventoNotFoundException {
		eventoRepository.modificaEventoAuladisponibile(idEvento, idAula);
		//aulaRepository.aggiornaAula(aulaDto.getIdaula(),aulaDto.getStato(),aulaDto.getCapienza());
	}
}
