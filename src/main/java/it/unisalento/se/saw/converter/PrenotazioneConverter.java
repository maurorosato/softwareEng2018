package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.PrenotazioneDto;

public class PrenotazioneConverter implements IConverter {
	public <T> Object domainToDto(T domainObject) {
		Prenotazione prenotazione = (Prenotazione) domainObject;
		
		PrenotazioneDto prenotazioneDto = new PrenotazioneDto();
		prenotazioneDto.setDataFine(prenotazione.getDataFine());
		prenotazioneDto.setDataInizio(prenotazione.getDataInizio());
		prenotazioneDto.setIdprenotazione(prenotazione.getIdprenotazione());
		prenotazioneDto.setIdDocente(prenotazione.getDocente().getIddocente());
		prenotazioneDto.setDocente(prenotazione.getDocente().getUtente().getNome() + ' ' + prenotazione.getDocente().getUtente().getCognome());

		return prenotazioneDto;
	}
	public <T> Object dtoToDomain(T dtoObject) {
		PrenotazioneDto prenotazioneDto = (PrenotazioneDto) dtoObject;
		Prenotazione prenotazione = new Prenotazione();
		
		Docente doc = new Docente();
		doc.setIddocente(prenotazioneDto.getIdDocente());
		
		prenotazione.setDataInizio(prenotazioneDto.getDataInizio());
		prenotazione.setDataFine(prenotazioneDto.getDataFine());
		prenotazione.setDocente(doc);
		
		return prenotazione;
		
	}

}
