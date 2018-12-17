package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.domain.Insegnamento;

import it.unisalento.se.saw.dto.InsegnamentoDto;
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
	
	@Override
	public List<Insegnamento> getAllInsegnamentiCorso(int idCorso) throws InsegnamentoNotFoundException {
		return insegnamentoRepository.getAllInsegnamentiCorso(idCorso);
	}
	
	@Transactional
	public Insegnamento save(Insegnamento insegnamento) {
		return insegnamentoRepository.save(insegnamento);
	}
	@Override
	public void aggiornaInsegnamento(InsegnamentoDto insegnamentoDto) throws InsegnamentoNotFoundException {
		insegnamentoRepository.aggiornaInsegnamento(insegnamentoDto.getCfu(),insegnamentoDto.getIdInsegnamento());
	}
	
	@Override
	public void rimuoviInsegnamento(int idInsegnamento) throws InsegnamentoNotFoundException {
		insegnamentoRepository.rimuoviInsegnamento(idInsegnamento);
	}
}
