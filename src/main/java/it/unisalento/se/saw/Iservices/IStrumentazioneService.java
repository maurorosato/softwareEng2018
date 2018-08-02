package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;

public interface IStrumentazioneService {
	
	public List<Strumentazione> getAll() throws StrumentazioneNotFoundException;
	public Strumentazione save(Strumentazione strumentazione);
}




