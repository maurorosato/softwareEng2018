package it.unisalento.se.saw.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.StrumentazioneDto;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
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
		return studenteRepository.save(studente);
	}
	
	@Override
	public void aggiornaStudente(StudenteDto studenteDto) throws StudenteNotFoundException {
		studenteRepository.aggiornaStudente(studenteDto.getIdStudente(),studenteDto.getIndirizzo());
	}
	//public void signingUpInsegnamento(int idStudente, int idInsegnamento){
	//	studenteRepository.signingUpStudenteInsegnamento(idStudente,idInsegnamento);
	//}

}
