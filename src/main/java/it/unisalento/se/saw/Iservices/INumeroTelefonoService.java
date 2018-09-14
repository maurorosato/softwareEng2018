package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;


public interface INumeroTelefonoService {
	public NumeroTelefono save(NumeroTelefono numeroTelefono);
	public List<NumeroTelefono> getAll() throws NumeroTelefonoNotFoundException;


}
