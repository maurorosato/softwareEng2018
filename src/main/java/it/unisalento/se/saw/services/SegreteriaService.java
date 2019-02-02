package it.unisalento.se.saw.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.unisalento.se.saw.Iservices.ISegreteriaService;
import it.unisalento.se.saw.domain.SegreteriaDidattica;
import it.unisalento.se.saw.exceptions.SegreteriaNotFoundException;
import it.unisalento.se.saw.repositories.SegreteriaRepository;

@Service
public class SegreteriaService implements ISegreteriaService {
	
	@Autowired
	SegreteriaRepository segreteriaRepository;
	
	@Override
	public List<SegreteriaDidattica> getAll() throws SegreteriaNotFoundException {
		return segreteriaRepository.findAll() ;
	}
}
