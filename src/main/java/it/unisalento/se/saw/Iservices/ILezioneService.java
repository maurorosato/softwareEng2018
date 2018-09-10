package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;

public interface ILezioneService {
	public List<Lezione> getAll() throws LezioneNotFoundException;

}
