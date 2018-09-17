package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.dto.AulaDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.repositories.AulaRepository;

@Service
public class AulaService implements IAulaService {
	
	@Autowired
	AulaRepository aulaRepository;
	
	@Override
	public List<Aula> getAll() throws AulaNotFoundException {
		return aulaRepository.findAll();
	}
	
	@Transactional
	public Aula save(Aula aula) {
		// TODO Auto-generated method stub
		return aulaRepository.save(aula);
	}
	
	@Override
	public Aula getById(int id) throws AulaNotFoundException {
		return aulaRepository.getOne(id);
	}
	
	@Override
	public void aggiornaAula(AulaDto aulaDto) throws AulaNotFoundException {
		aulaRepository.aggiornaAula(aulaDto.getIdAula(),aulaDto.getStato(),aulaDto.getCapienza());
	}
}