package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;
import it.unisalento.se.saw.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService implements IPrenotazioneService {
	
	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	
	@Override
	public List<Prenotazione> getAll() throws PrenotazioneNotFoundException {
		return prenotazioneRepository.findAll();
	}
}
