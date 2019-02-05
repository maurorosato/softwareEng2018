package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Valutazione;
import it.unisalento.se.saw.dto.ValutazioneDto;

public class ValutazioneConverter implements IConverter{
	public <T> Object dtoToDomain(T dtoObject) {
		ValutazioneDto valutazioneDto = (ValutazioneDto) dtoObject;
		
		Lezione lezione = new Lezione();
		Valutazione valutazione = new Valutazione();
		
		lezione.setIdlezione(valutazioneDto.getIdLezione());
		valutazione.setLezione(lezione);
		valutazione.setNota(valutazioneDto.getNota());
		valutazione.setValutazione(valutazioneDto.getValutazione());
		valutazione.setMaterialeDidatticoIdmaterialeDidattico(valutazioneDto.getIdMaterialeDidattico());
		
		return valutazione;
	}
	
	public <T> Object domainToDto(T domainObject) {
		Valutazione valutazione = (Valutazione) domainObject;
		ValutazioneDto valutazioneDto = new ValutazioneDto();
		
		valutazioneDto.setIdValutazione(valutazione.getIdvalutazione());
		valutazioneDto.setIdLezione(valutazione.getLezione().getIdlezione());
		valutazioneDto.setNota(valutazione.getNota());
		valutazioneDto.setValutazione(valutazione.getValutazione());
		valutazioneDto.setIdStudente(valutazione.getStudente().getIdstudente());
		valutazioneDto.setIdMaterialeDidattico(valutazione.getMaterialeDidatticoIdmaterialeDidattico());
		
		return valutazioneDto;
	}
}
