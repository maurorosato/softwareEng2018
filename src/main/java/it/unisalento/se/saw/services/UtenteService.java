package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;
import it.unisalento.se.saw.repositories.UtenteRepository;

@Service
public class UtenteService implements IUtenteService {
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Override
	public List<Utente> getAll() throws UtenteNotFoundException {
		return utenteRepository.findAll() ;
	}

	@Transactional
	public Utente save(Utente user) {
		// TODO Auto-generated method stub
		return utenteRepository.save(user);
	}

	@Override
	public Utente getById(int id) throws UtenteNotFoundException {
		return utenteRepository.getOne(id);
	}

	@Override
	public void removeUserById(int id) throws UtenteNotFoundException {
	}
	
	@Override
	public Utente autenticazione(String email,String password) throws UtenteNotFoundException{
		//return utenteRepository.autenticazione(email,password);
		return (utenteRepository.findByEmailAndPassword(email, password)).get(0);
	}

}
