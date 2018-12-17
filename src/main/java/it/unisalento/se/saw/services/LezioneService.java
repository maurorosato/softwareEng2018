package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.domain.Insegnamento;
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

	@Override
	public List<Lezione> getLezioniInsegnamento(Insegnamento insegnamento) throws LezioneNotFoundException {
		return lezioneRepository.getLezioniInsegnamento(insegnamento);
	}
	
	@Transactional
	public Lezione save(Lezione lezione) {
		return lezioneRepository.save(lezione);
	}
	
	@Override
	public Lezione getById(int id) throws LezioneNotFoundException {
		return lezioneRepository.getOne(id);
	}

	@Override
	public List<Lezione> getAllStudente(int idCorsoStudente, int annoCorso) throws LezioneNotFoundException{
		return lezioneRepository.getAllStudente(idCorsoStudente, annoCorso);
	}

}
