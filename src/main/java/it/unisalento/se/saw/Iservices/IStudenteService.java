package it.unisalento.se.saw.Iservices;

import java.util.List;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;


public interface IStudenteService {
	public List<Studente> getAll() throws StudenteNotFoundException;
	public void aggiornaStudente(StudenteDto studenteDto) throws StudenteNotFoundException;
	public Studente save(Studente studente);
}
