package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
import it.unisalento.se.saw.repositories.InsegnamentoRepository;

@Service
public class InsegnamentoService implements IInsegnamentoService {

	@Autowired
	InsegnamentoRepository insegnamentoRepository;
	
	@Override
	public List<Insegnamento> getAll() throws InsegnamentoNotFoundException {
		return insegnamentoRepository.findAll();
	}
	@Transactional
	public Insegnamento save(Insegnamento insegnamento) {
		// TODO Auto-generated method stub
		return insegnamentoRepository.save(insegnamento);
		//return null;
	}

}
