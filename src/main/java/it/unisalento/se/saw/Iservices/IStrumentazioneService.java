package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.dto.StrumentazioneDto;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;

public interface IStrumentazioneService {
	
	public List<Strumentazione> getAll() throws StrumentazioneNotFoundException;
	public void aggiornaStrumentazione(StrumentazioneDto strumentazioneDto) throws StrumentazioneNotFoundException;
	public void rimuoviStrumentazione(int idStrumentazione) throws StrumentazioneNotFoundException;
	public Strumentazione save(Strumentazione strumentazione);
	public List<Strumentazione> getValidateStrumentazioneOfSegnalazione(String descrizione, int idAula) throws StrumentazioneNotFoundException;

}




