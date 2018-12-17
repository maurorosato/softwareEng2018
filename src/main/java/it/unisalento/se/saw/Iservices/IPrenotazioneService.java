package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;

public interface IPrenotazioneService {
	public Prenotazione save(Prenotazione prenotazione);
	public List<Prenotazione> getAll() throws PrenotazioneNotFoundException;
}
