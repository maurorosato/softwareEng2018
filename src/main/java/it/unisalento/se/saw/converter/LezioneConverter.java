package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.LezioneDto;

public class LezioneConverter {
	public static LezioneDto domainToDto(Lezione lezione, List<Prenotazione> prenotazioni, List<Evento> eventi, List<Insegnamento> insegnamenti,List<Aula> aule,List<Docente> docenti ){
		LezioneDto lezioneDto = new LezioneDto();
		
		Iterator<Aula> aulaIterator = aule.iterator();
		Iterator<Evento> eventoIterator = eventi.iterator();
		Iterator<Docente> docenteIterator = docenti.iterator();
		Iterator<Prenotazione> prenotazioneIterator = prenotazioni.iterator();
		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();

		int idAula = 0;
		int idDocente = 0;
		int idInsegnamento = 0;
		
		lezioneDto.setIdLezione(lezione.getIdlezione());
		lezioneDto.setIdEventoLezione(lezione.getEvento().getIdevento());
		
		while(eventoIterator.hasNext()){
			Evento evento = eventoIterator.next();
			if(lezioneDto.getIdEventoLezione() == evento.getIdevento() ){
				lezioneDto.setIdPrenotazioneLezione(evento.getPrenotazione().getIdprenotazione());
				lezioneDto.setDescrizione(evento.getDescrizione());
				idAula = evento.getAula().getIdaula();
				idInsegnamento = evento.getInsegnamento().getIdinsegnamento();
			}
		}
		
		while(aulaIterator.hasNext()){
			Aula aula = aulaIterator.next();
			if (idAula == aula.getIdaula()){
				lezioneDto.setAula(aula.getNome());
				lezioneDto.setLatitudine(aula.getLatitudine());
				lezioneDto.setLongitudine(aula.getLongitudine());
			}
		}
				
		while(prenotazioneIterator.hasNext()){
			Prenotazione prenotazione = prenotazioneIterator.next();
			if (lezioneDto.getIdPrenotazioneLezione() == prenotazione.getIdprenotazione()){
				idDocente = prenotazione.getDocente().getIddocente();
				lezioneDto.setDataInizio(prenotazione.getDataInizio());
				lezioneDto.setDataFine(prenotazione.getDataFine());
			}
		}
		
		while(docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			if(idDocente == docente.getIddocente()){
				lezioneDto.setDocente(docente.getUtente().getNome() + " " + docente.getUtente().getCognome());
				lezioneDto.setIdUserDocente(docente.getUtente().getIdutente());
			}
		}
		
		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			if (idInsegnamento == insegnamento.getIdinsegnamento()){
				lezioneDto.setInsegnamento(insegnamento.getNome());
			}
		}
		return lezioneDto;	
	}	
}
