package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.repositories.LezioneRepository;

@Service
public class LezioneService implements ILezioneService {

	
	@Autowired
	LezioneRepository lezioneRepository;
	
	@Override
	public List<Lezione> getAll() throws LezioneNotFoundException {
		return lezioneRepository.findAll();
	}

}
