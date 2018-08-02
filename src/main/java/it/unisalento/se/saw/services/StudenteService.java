package it.unisalento.se.saw.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.repositories.StudenteRepository;

@Service
public class StudenteService implements IStudenteService {
	
	@Autowired
	StudenteRepository studenteRepository;
	
	@Override
	public List<Studente> getAll() throws StudenteNotFoundException {
		return studenteRepository.findAll() ;
	}

	@Transactional
	public Studente save(Studente studente) {
		// TODO Auto-generated method stub
		return studenteRepository.save(studente);
	}

}
