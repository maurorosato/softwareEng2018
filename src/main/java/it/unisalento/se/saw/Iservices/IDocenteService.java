package it.unisalento.se.saw.Iservices;

import java.util.List;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;

public interface IDocenteService {
	
	public List<Docente> getAll() throws DocenteNotFoundException;
	public Docente save(Docente docente);
	public Docente getById(int id) throws DocenteNotFoundException;
	public void remove(int id) throws DocenteNotFoundException;
	public void aggiornaDocente(DocenteDto docenteDto) throws DocenteNotFoundException;

}
