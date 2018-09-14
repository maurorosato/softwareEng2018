package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.dto.StrumentazioneDto;

public class StrumentazioneConverter {

	public static StrumentazioneDto domainToDto(Strumentazione strumentazione){
		StrumentazioneDto strumentazioneDto = new StrumentazioneDto();
		
		strumentazioneDto.setIdStrumentazione(strumentazione.getIdstrumentazione());
		strumentazioneDto.setDescrizione(strumentazione.getDescrizione());
		strumentazioneDto.setStato(strumentazione.getStato());
		strumentazioneDto.setAulaRiferimento(strumentazione.getAula().getNome());
		
		return strumentazioneDto;
	}
	
	public static Strumentazione dtoToDomain(StrumentazioneDto strumentazioneDto, Aula aula){
		Strumentazione strumentazione = new Strumentazione();
		strumentazione.setDescrizione(strumentazioneDto.getDescrizione());
		strumentazione.setStato(strumentazioneDto.getStato());	
		strumentazione.setAula(aula);
		
		return strumentazione;
		
	}
}
