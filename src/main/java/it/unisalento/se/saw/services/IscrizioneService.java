package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IIscrizioneService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.exceptions.IscrizioneNotFoundException;
import it.unisalento.se.saw.repositories.IscrizioneRepository;

@Service
public class IscrizioneService implements IIscrizioneService{
	
	@Autowired
	IscrizioneRepository iscrizioneRepository;
	
	@Transactional
	public void save(Iscrizione iscrizione) throws IscrizioneNotFoundException {
		iscrizioneRepository.save(iscrizione);
	}
	
	@Override
	public List<Iscrizione> getAll() throws IscrizioneNotFoundException{
		return iscrizioneRepository.findAll();
	}

	@Override
	public List<Iscrizione> getIscrizione(Insegnamento insegnamento, Studente studente) throws IscrizioneNotFoundException{
		System.out.println("Insegnamento service " +insegnamento.getIdinsegnamento());
		System.out.println("Studente service " +studente.getIdstudente());

		return iscrizioneRepository.getIscrizione(insegnamento,studente);
	}
}
