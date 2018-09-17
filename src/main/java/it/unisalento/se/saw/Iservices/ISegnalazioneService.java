package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.dto.SegnalazioneDto;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;

public interface ISegnalazioneService {
	public List<Segnalazione> getAll() throws SegnalazioneNotFoundException;
	public Segnalazione getById(int id) throws SegnalazioneNotFoundException;
	public void cambiaStatoSegnalazione(SegnalazioneDto segnalazioneDto) throws SegnalazioneNotFoundException;
	public Segnalazione save(Segnalazione segnalazione);
}
