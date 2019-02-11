package it.unisalento.se.saw.Iservices;

import java.util.List;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;

public interface IDocenteService {
	
	public Docente save(Docente docente);
	public void remove(int id) throws DocenteNotFoundException;
	public List<Docente> getAll() throws DocenteNotFoundException;
	public Docente getById(int id) throws DocenteNotFoundException;
	public Docente findByUtente(Utente utente) throws DocenteNotFoundException;
	public void aggiornaDocente(DocenteDto docenteDto) throws DocenteNotFoundException;

}
