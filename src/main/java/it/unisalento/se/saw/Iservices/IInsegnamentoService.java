package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.InsegnamentoDto;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;


public interface IInsegnamentoService {
	public List<Insegnamento> getAll() throws InsegnamentoNotFoundException;
	public void aggiornaInsegnamento(InsegnamentoDto insegnamentoDto) throws InsegnamentoNotFoundException;
	public Insegnamento save(Insegnamento insegnamento);

}
