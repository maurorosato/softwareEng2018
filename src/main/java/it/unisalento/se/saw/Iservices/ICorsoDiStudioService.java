package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;

public interface ICorsoDiStudioService {
	public List<CorsoDiStudio> getAll() throws CorsoDiStudioNotFoundException;
	public CorsoDiStudio save(CorsoDiStudio corso);
	public CorsoDiStudio getById(int id) throws CorsoDiStudioNotFoundException;

}
