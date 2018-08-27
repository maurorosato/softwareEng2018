package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;

public interface ISegnalazioneService {
	public List<Segnalazione> getAll() throws SegnalazioneNotFoundException;
}
