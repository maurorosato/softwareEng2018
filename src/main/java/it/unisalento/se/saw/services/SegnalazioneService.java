package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.ISegnalazioneService;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;
import it.unisalento.se.saw.repositories.SegnalazioneRepository;


@Service
public class SegnalazioneService implements ISegnalazioneService{
	
	@Autowired
	SegnalazioneRepository segnalazioneRepository;
	
	@Override
	public List<Segnalazione> getAll() throws SegnalazioneNotFoundException {
		return segnalazioneRepository.findAll();
	}

}
