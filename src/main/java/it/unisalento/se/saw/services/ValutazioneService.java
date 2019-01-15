package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IValutazioneService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Valutazione;
import it.unisalento.se.saw.dto.ValutazioneDto;
import it.unisalento.se.saw.exceptions.MaterialeDidatticoNotFoundException;
import it.unisalento.se.saw.repositories.ValutazioneRepository;

@Service
public class ValutazioneService implements IValutazioneService{

	@Autowired
	ValutazioneRepository valutazioneRepository;
	
	@Transactional
	public void save(Valutazione valutazione) {
		valutazioneRepository.save(valutazione);
	}
	
	@Override
	public List<Valutazione> getValutazioneStudenteLezione(Studente studente,Lezione lezione) {
		return valutazioneRepository.getValutazioneStudenteLezione(studente,lezione);
	}
	
	@Override
	public List<Valutazione> getValutazioneLezione(Lezione lezione) {
		return valutazioneRepository.getValutazioneLezione(lezione);
	}
	
	@Override
	public List<Valutazione> getValutazioneStudenteMatDidattico(Studente studente,int idMatDidattico) {
		return valutazioneRepository.getValutazioneStudenteMatDidattico(studente,idMatDidattico);
	}
	
	@Override
	public List<Valutazione> getValutazioneMatDidattico(int idMatDidattico) {
		return valutazioneRepository.getValutazioneMatDidattico(idMatDidattico);
	}
}
