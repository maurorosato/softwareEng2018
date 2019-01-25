package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.exceptions.IscrizioneNotFoundException;

public interface IIscrizioneService {
	public List<Iscrizione> getAll() throws IscrizioneNotFoundException;
	public void save(Iscrizione iscrizione) throws IscrizioneNotFoundException;
	public void rimuoviIscrizione(Iscrizione iscrizione) throws IscrizioneNotFoundException;
	public List<Iscrizione> getIscrizione(Insegnamento insegnamento, Studente studente) throws IscrizioneNotFoundException;
	
}
