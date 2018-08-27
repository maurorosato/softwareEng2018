package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IAulaPrenotazioneEventoService;
import it.unisalento.se.saw.domain.AulaPrenotazioneEvento;
import it.unisalento.se.saw.exceptions.AulaPrenotazioneEventoNotFoundException;
import it.unisalento.se.saw.repositories.AulaPrenotazioneEventoRepository;

@Service
public class AulaPrenotazioneEventoService implements IAulaPrenotazioneEventoService{

	@Autowired
	AulaPrenotazioneEventoRepository aulaPrenotazioneEventoRepositoryRepository;
	
	@Override
	public List<AulaPrenotazioneEvento> getAll() throws AulaPrenotazioneEventoNotFoundException {
		return aulaPrenotazioneEventoRepositoryRepository.findAll();
	}
}
