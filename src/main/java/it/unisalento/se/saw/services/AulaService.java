package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.domain.Aula;
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
}