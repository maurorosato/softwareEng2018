package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.PrenotazioneDto;

public class PrenotazioneConverter {
	
	public static PrenotazioneDto domainToDto (Prenotazione prenotazione, List<Docente> docenti){
		PrenotazioneDto prenotazioneDto = new PrenotazioneDto();
		String docenteSegnalante = null;
		Iterator<Docente> docenteIterator = docenti.iterator();
		
		while (docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			if(prenotazione.getDocente().getIddocente() == docente.getIddocente()){
				docenteSegnalante = docente.getUtente().getNome() + ' ' + docente.getUtente().getCognome();
			}
		}

		java.util.Date sqlDate = prenotazione.getDataEvento();
		prenotazioneDto.setData(sqlDate.toString());
		
		prenotazioneDto.setIdPrenotazione(prenotazione.getIdprenotazione());
		prenotazioneDto.setDurata(prenotazione.getDurata());
		prenotazioneDto.setDocente(docenteSegnalante);
		
		return prenotazioneDto;
	}

}
