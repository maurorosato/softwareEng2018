package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.exceptions.MaterialeDidatticoNotFoundException;

public interface IMaterialeDidatticoService {
	public List<MaterialeDidattico> getAll() throws MaterialeDidatticoNotFoundException;
	public List<MaterialeDidattico> getAllLezione(Lezione lezione) throws MaterialeDidatticoNotFoundException;
	public MaterialeDidattico save(MaterialeDidattico materialeDidatticoDto);
}
