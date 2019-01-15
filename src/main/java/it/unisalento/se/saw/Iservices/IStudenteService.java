package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;


public interface IStudenteService {
	public Studente save(Studente studente);
	public List<Studente> getAll() throws StudenteNotFoundException;
	public List<Studente> getStudenteIscrittoInsegnamento(Insegnamento insegnamento) throws StudenteNotFoundException;
	public void aggiornaStudente(StudenteDto studenteDto) throws StudenteNotFoundException;
}
