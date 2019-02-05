package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.InsegnamentoDto;

public class InsegnamentoConverter implements IConverter {

	public <T> Object dtoToDomain(T dtoObject) {
		InsegnamentoDto insegnamentoDto = (InsegnamentoDto) dtoObject;
		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setCfu(insegnamentoDto.getCfu());
		insegnamento.setNome(insegnamentoDto.getNome());

		return insegnamento;
	}
	
	public <T> Object domainToDto(T domainObject) {
		Insegnamento insegnamento = (Insegnamento) domainObject;
		InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
		
		insegnamentoDto.setCfu(insegnamento.getCfu());
		insegnamentoDto.setNome(insegnamento.getNome());
		insegnamentoDto.setIdInsegnamento(insegnamento.getIdinsegnamento());
		insegnamentoDto.setIdUserDocente(insegnamento.getDocente().getUtente().getIdutente());
		insegnamentoDto.setDocente(insegnamento.getDocente().getUtente().getNome() + ' ' + insegnamento.getDocente().getUtente().getCognome());

		return insegnamentoDto;
	}
}
