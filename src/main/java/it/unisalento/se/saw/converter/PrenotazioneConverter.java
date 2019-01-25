package it.unisalento.se.saw.converter;

import java.util.Iterator;


import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.PrenotazioneDto;

public class PrenotazioneConverter {
	
	public static PrenotazioneDto domainToDto (Prenotazione prenotazione /*,List<Docente> docenti*/){
		PrenotazioneDto prenotazioneDto = new PrenotazioneDto();
		prenotazioneDto.setDataFine(prenotazione.getDataFine());
		prenotazioneDto.setDataInizio(prenotazione.getDataInizio());
		prenotazioneDto.setIdprenotazione(prenotazione.getIdprenotazione());
		prenotazioneDto.setIdDocente(prenotazione.getDocente().getIddocente());
		prenotazioneDto.setDocente(prenotazione.getDocente().getUtente().getNome() + ' ' + prenotazione.getDocente().getUtente().getCognome());

		return prenotazioneDto;
	}
	
	public static Prenotazione dtoToDomain(PrenotazioneDto prenotazioneDto) {
		Prenotazione prenotazione = new Prenotazione();
		
		Docente doc = new Docente();
		doc.setIddocente(prenotazioneDto.getIdDocente());
		
		prenotazione.setDataInizio(prenotazioneDto.getDataInizio());
		prenotazione.setDataFine(prenotazioneDto.getDataFine());
		prenotazione.setDocente(doc);
		
		return prenotazione;
		
	}

}
