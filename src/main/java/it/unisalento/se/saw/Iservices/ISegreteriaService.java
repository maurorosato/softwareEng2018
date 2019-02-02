package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.SegreteriaDidattica;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.SegreteriaNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;


public interface ISegreteriaService {
	public List<SegreteriaDidattica> getAll() throws SegreteriaNotFoundException;
}
