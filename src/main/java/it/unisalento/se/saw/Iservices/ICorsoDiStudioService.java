package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;


public interface ICorsoDiStudioService {
	public List<CorsoDiStudio> getAll() throws CorsoDiStudioNotFoundException;
	public CorsoDiStudio save(CorsoDiStudio corso);
	public CorsoDiStudio getById(int id) throws CorsoDiStudioNotFoundException;
	public void rimuoviCorsoDiStudio(int idCorso) throws CorsoDiStudioNotFoundException;
}
