package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.dto.StrumentazioneDto;

public class StrumentazioneConverter implements IConverter {
	@Override
	public <T> Object domainToDto(T domainObject) {
	//public static StrumentazioneDto domainToDto(Strumentazione strumentazione){
		StrumentazioneDto strumentazioneDto = new StrumentazioneDto();
		Strumentazione strumentazione = (Strumentazione) domainObject;
		
		strumentazioneDto.setStato(strumentazione.getStato());
		strumentazioneDto.setDescrizione(strumentazione.getDescrizione());
		strumentazioneDto.setAulaRiferimento(strumentazione.getAula().getNome());
		strumentazioneDto.setIdStrumentazione(strumentazione.getIdstrumentazione());
		
		return strumentazioneDto;
	}
	@Override
	public <T> Object dtoToDomain(T dtoObject) {
		StrumentazioneDto strumentazioneDto = (StrumentazioneDto) dtoObject;
	//public static Strumentazione dtoToDomain(StrumentazioneDto strumentazioneDto, Aula aula){
		Strumentazione strumentazione = new Strumentazione();
		strumentazione.setDescrizione(strumentazioneDto.getDescrizione());
		strumentazione.setStato(strumentazioneDto.getStato());	
		//strumentazione.setAula(aula);
		
		return strumentazione;
		
	}
}
