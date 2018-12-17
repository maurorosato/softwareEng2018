package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;

public interface ILezioneService {
	public List<Lezione> getAll() throws LezioneNotFoundException;
	public List<Lezione> getLezioniInsegnamento(Insegnamento insegnamento) throws LezioneNotFoundException;
	public List<Lezione> getAllStudente(int idCorsoStudente, int annoCorso) throws LezioneNotFoundException;
	public Lezione getById(int id) throws LezioneNotFoundException;
	public Lezione save(Lezione lezione);
}
