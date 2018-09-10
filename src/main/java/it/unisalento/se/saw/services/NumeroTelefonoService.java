package it.unisalento.se.saw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.repositories.NumeroTelefonoRepository;

@Service
public class NumeroTelefonoService implements INumeroTelefonoService {

	@Autowired
	NumeroTelefonoRepository numeroTelefonoRepository;
	
	@Transactional
	public NumeroTelefono save(NumeroTelefono numeroTelefono) {
		// TODO Auto-generated method stub
		return numeroTelefonoRepository.save(numeroTelefono);
		//return null;
	}
}
