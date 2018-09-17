package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ISegnalazioneService;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.dto.SegnalazioneDto;
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
	@Transactional
	public Segnalazione save(Segnalazione segnalazione) {
		return segnalazioneRepository.save(segnalazione);
	}
	
	@Override
	public Segnalazione getById(int id) throws SegnalazioneNotFoundException {
<<<<<<< HEAD
=======
		// TODO Auto-generated method stub
>>>>>>> d6a6dddd75bd759d8aeb54d078063d0fcc04c7ce
		return segnalazioneRepository.getOne(id);
	}
	
	@Override
    public void cambiaStatoSegnalazione(SegnalazioneDto segnalazioneDto) throws SegnalazioneNotFoundException {
         segnalazioneRepository.cambiaStatoSegnalazione(segnalazioneDto.getIdSegnalazione(), segnalazioneDto.getStatoSegnalazione());  
	}
        
}
