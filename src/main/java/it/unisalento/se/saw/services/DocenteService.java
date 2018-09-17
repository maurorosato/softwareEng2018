package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.dto.StrumentazioneDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
import it.unisalento.se.saw.repositories.DocenteRepository;

@Service
public class DocenteService implements IDocenteService {
	
	@Autowired
	DocenteRepository docenteRepository;
	
	@Override
	public List<Docente> getAll() throws DocenteNotFoundException {
		return docenteRepository.findAll() ;
	}

	@Transactional
	public Docente save(Docente docente) {
		// TODO Auto-generated method stub
		return docenteRepository.save(docente);
	}

	@Override
	public Docente getById(int id) throws DocenteNotFoundException {
		return docenteRepository.getOne(id);
	}

	@Override
	public void remove(int id) throws DocenteNotFoundException {		
	}
	
	@Override
	public void aggiornaDocente(DocenteDto docenteDto) throws DocenteNotFoundException {
		docenteRepository.aggiornaDocente(docenteDto.getIdDocente(),docenteDto.getGrado(),docenteDto.getStipendio());
	}
}
