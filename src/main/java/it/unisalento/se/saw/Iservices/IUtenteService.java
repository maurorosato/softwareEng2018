package it.unisalento.se.saw.Iservices;

import java.util.List;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

public interface IUtenteService {
	
	public List<Utente> getAll() throws UtenteNotFoundException;
	public Utente autenticazione(String email, String password) throws UtenteNotFoundException;
	public Utente save(Utente user);
	public Utente getById(int id) throws UtenteNotFoundException;
	public void removeUserById(int id) throws UtenteNotFoundException;
	
}
