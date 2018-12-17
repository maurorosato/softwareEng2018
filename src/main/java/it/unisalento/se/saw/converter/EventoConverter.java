package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.EventoDto;
import it.unisalento.se.saw.dto.PrenotazioneDto;

public class EventoConverter {
	
	public static Evento dtoToDomain(PrenotazioneDto prenotazioneDto, List<Insegnamento> insegnamenti){
		Evento evento = new Evento();
		Aula aula = new Aula();
		Insegnamento insegnamento = new Insegnamento();
		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
		
		while(insegnamentoIterator.hasNext()){
			Insegnamento i = insegnamentoIterator.next();
			if (i.getNome().equals(prenotazioneDto.getInsegnamento())){
				insegnamento.setIdinsegnamento(i.getIdinsegnamento());
			}		
		}
		aula.setIdaula(1);
		
		evento.setInsegnamento(insegnamento);
		evento.setAula(aula);
		
		return evento;
	}

	public static EventoDto domainToDto(Evento evento, List<Aula> aule, List<Utente> utenti,List<Lezione> lezioni,List<Docente> docenti,List<AppelloEsame> appelli,List<CorsoDiStudio> corsi,List<Prenotazione> prenotazioni,List<Insegnamento> insegnamenti){
		EventoDto eventoDto = new EventoDto();

		float numeroFloat = 0;
		int idCorso = 0,idDocente = 0, idUtente = 0;
		
		Iterator<Aula> aulaIterator = aule.iterator();
		Iterator<Utente> utenteIterator = utenti.iterator();
		Iterator<Lezione> lezioneIterator = lezioni.iterator();
		Iterator<Docente> docenteIterator = docenti.iterator();			
		Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
		Iterator<AppelloEsame> appelloIterator = appelli.iterator();
		Iterator<Prenotazione> prenotazioneIterator = prenotazioni.iterator();
		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();

		eventoDto.setIdEvento(evento.getIdevento());
		eventoDto.setDescrizione(evento.getDescrizione());
		eventoDto.setGradimento(numeroFloat);
		eventoDto.setLezioneOrEsame("esame");
		eventoDto.setImage("examsIcon.png");

		
		while (aulaIterator.hasNext()){
			Aula aula = aulaIterator.next();
			if (aula.getIdaula() == evento.getAula().getIdaula()){
				eventoDto.setAula(aula.getNome());
				eventoDto.setIdAula(evento.getAula().getIdaula());
			}
		}
		
		while (insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			if (insegnamento.getIdinsegnamento() == evento.getInsegnamento().getIdinsegnamento()){
				eventoDto.setInsegnamento(insegnamento.getNome());
				idCorso = insegnamento.getCorsoDiStudioIdcorsoDiStudio();
				idDocente = insegnamento.getDocente().getIddocente();
			}
		}
		
		while (corsoIterator.hasNext()){
			CorsoDiStudio corso = corsoIterator.next();
			if (corso.getIdcorsoDiStudio() == idCorso)
				eventoDto.setCorso(corso.getNomeCorso());
		}
		
		while (lezioneIterator.hasNext()){
			Lezione lezione = lezioneIterator.next();
			if (lezione.getEvento().getIdevento() == evento.getIdevento()){
				eventoDto.setGradimento(lezione.getGradimento());
				eventoDto.setImage("lessonsIcon.png");
				eventoDto.setLezioneOrEsame("lezione");
			}
		}
		
		while (prenotazioneIterator.hasNext()){
			Prenotazione prenotazione = prenotazioneIterator.next();
			if (prenotazione.getIdprenotazione() == evento.getPrenotazione().getIdprenotazione()){
				eventoDto.setDataInizio(prenotazione.getDataInizio());
				eventoDto.setDataFine(prenotazione.getDataFine());
			}
		}

		while (appelloIterator.hasNext()){
			AppelloEsame appello = appelloIterator.next();
			if (appello.getEvento().getIdevento() == evento.getIdevento())
				eventoDto.setDescrizione("TIPOLOGIA: "+appello.getTipologia() + " DESCRIZIONE: "+ evento.getDescrizione());
		}
		
		while (docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			if (docente.getIddocente() == idDocente)
				idUtente = docente.getUtente().getIdutente();
				eventoDto.setIdUtente(idUtente);
		}

		while (utenteIterator.hasNext()){
			Utente utente = utenteIterator.next();
			if (utente.getIdutente() == idUtente)
				eventoDto.setDocente(utente.getNome() + " " + utente.getCognome());
		}
		return eventoDto;
	}
}
