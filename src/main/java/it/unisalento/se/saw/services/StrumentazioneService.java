package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IStrumentazioneService;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
import it.unisalento.se.saw.repositories.StrumentazioneRepository;


@Service
public class StrumentazioneService implements IStrumentazioneService {
	@Autowired
	StrumentazioneRepository strumentazioneRepository;
	
	@Override
	public List<Strumentazione> getAll() throws StrumentazioneNotFoundException {
		return strumentazioneRepository.findAll();
	}

	@Transactional
	public Strumentazione save(Strumentazione strumentazione) {
		// TODO Auto-generated method stub
		return strumentazioneRepository.save(strumentazione);
		//return null;
	}
}
