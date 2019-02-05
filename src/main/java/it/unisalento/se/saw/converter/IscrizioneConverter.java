package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.dto.IscrizioneDto;

public class IscrizioneConverter implements IConverter {
	public <T> Object dtoToDomain(T dtoObject) {
		IscrizioneDto iscrizioneDto = (IscrizioneDto) dtoObject;
		Iscrizione iscrizione = new Iscrizione();
		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setIdinsegnamento(iscrizioneDto.getIdInsegnamento());
		
		iscrizione.setInsegnamento(insegnamento);
		iscrizione.setStato(iscrizioneDto.getStato());
		
		return iscrizione;
	}
	public <T> Object domainToDto(T domainObject) {
		IscrizioneDto iscrizioneDto = new IscrizioneDto();
		Iscrizione iscrizione = (Iscrizione) domainObject;
		
		iscrizioneDto.setStato(iscrizione.getStato());
		iscrizioneDto.setIdIscrizione(iscrizione.getIdiscrizione());
		iscrizioneDto.setIdInsegnamento(iscrizione.getInsegnamento().getIdinsegnamento());
		iscrizioneDto.setIdUserStudente(iscrizione.getStudente().getUtente().getIdutente());
		return iscrizioneDto;
	}
}
