package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.AppelloEsameDto;
import it.unisalento.se.saw.dto.PrenotazioneDto;

public class AppelloEsameConverter {
	
	public static AppelloEsame dtoToDomain(PrenotazioneDto prenotazioneDto){
		AppelloEsame appelloEsame = new AppelloEsame();
		appelloEsame.setTipologia(prenotazioneDto.getTipologiaEsame());
		
		return appelloEsame;
	}
	
	public static AppelloEsameDto domainToDto(AppelloEsame appelloEsame, List<Prenotazione> prenotazioni, List<Evento> eventi, List<Insegnamento> insegnamenti,List<Aula> aule,List<Docente> docenti ){
		AppelloEsameDto appelloEsameDto = new AppelloEsameDto();
		appelloEsameDto.setIdAppelloEsame(appelloEsame.getIdappelloEsame());
		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
		Iterator<Aula> aulaIterator = aule.iterator();
		Iterator<Prenotazione> prenotazioneIterator = prenotazioni.iterator();
		Iterator<Evento> eventoIterator = eventi.iterator();
		Iterator<Docente> docenteIterator = docenti.iterator();
		int idAula = 0;
		int idDocente = 0;
		int idInsegnamento = 0;
		
		appelloEsameDto.setIdAppelloEsame(appelloEsame.getIdappelloEsame());
		appelloEsameDto.setIdEventoAppelloEsame(appelloEsame.getEvento().getIdevento());
		appelloEsameDto.setTipologia(appelloEsame.getTipologia());
		
		while(eventoIterator.hasNext()){
			Evento evento = eventoIterator.next();
			if(appelloEsameDto.getIdEventoAppelloEsame() == evento.getIdevento() ){
				appelloEsameDto.setDescrizione(evento.getDescrizione());
				appelloEsameDto.setIdPrenotazioneAppelloEsame(evento.getPrenotazione().getIdprenotazione());
				
				idAula = evento.getAula().getIdaula();
				idInsegnamento = evento.getInsegnamento().getIdinsegnamento();
			}
		}
		
		while(aulaIterator.hasNext()){
			Aula aula = aulaIterator.next();
			if (idAula == aula.getIdaula()){
				appelloEsameDto.setAula(aula.getNome());
			}
		}
		
		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			if (idInsegnamento == insegnamento.getIdinsegnamento()){
				appelloEsameDto.setInsegnamento(insegnamento.getNome());
			}
		}
		
		while(prenotazioneIterator.hasNext()){
			Prenotazione prenotazione = prenotazioneIterator.next();
			if (appelloEsameDto.getIdPrenotazioneAppelloEsame() == prenotazione.getIdprenotazione()){
				idDocente = prenotazione.getDocente().getIddocente();
				appelloEsameDto.setDataInizio(prenotazione.getDataInizio());
				appelloEsameDto.setDataFine(prenotazione.getDataFine());
			}
		}
		
		while(docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			if(idDocente == docente.getIddocente()){
				appelloEsameDto.setDocente(docente.getUtente().getNome() + " " + docente.getUtente().getCognome());
			}
		}
		return appelloEsameDto;
	}
}