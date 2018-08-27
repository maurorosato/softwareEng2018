package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.AulaPrenotazioneEvento;
import it.unisalento.se.saw.exceptions.AulaPrenotazioneEventoNotFoundException;

public interface IAulaPrenotazioneEventoService {
	public List<AulaPrenotazioneEvento> getAll() throws AulaPrenotazioneEventoNotFoundException;
}
