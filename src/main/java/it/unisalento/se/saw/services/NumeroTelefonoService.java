package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;
import it.unisalento.se.saw.repositories.NumeroTelefonoRepository;

@Service
public class NumeroTelefonoService implements INumeroTelefonoService {

	@Autowired
	NumeroTelefonoRepository numeroTelefonoRepository;
	
	@Transactional
	public NumeroTelefono save(NumeroTelefono numeroTelefono) {
		return numeroTelefonoRepository.save(numeroTelefono);
	}
	
	@Override
	public List<NumeroTelefono> getAll() throws NumeroTelefonoNotFoundException {
		return numeroTelefonoRepository.findAll();
	}
}
