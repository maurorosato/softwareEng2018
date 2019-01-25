package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.IscrizioneDto;

public class IscrizioneConverter {
	public static Iscrizione DtoToDomain(IscrizioneDto iscrizioneDto, List<Studente> studenti){
		Studente studente = new Studente();
		Studente student = new Studente();
		Iscrizione iscrizione = new Iscrizione();
		Insegnamento insegnamento = new Insegnamento();
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			student = studenteIterator.next();
			if(student.getUtente().getIdutente() == iscrizioneDto.getIdUserStudente()){
				studente.setIdstudente(student.getIdstudente());
			}
		}
		insegnamento.setIdinsegnamento(iscrizioneDto.getIdInsegnamento());
		
		iscrizione.setInsegnamento(insegnamento);
		iscrizione.setStudente(studente);
		iscrizione.setStato(iscrizioneDto.getStato());
		
		return iscrizione;
	}
	
	public static IscrizioneDto DomainToDto(Iscrizione iscrizione, List<Studente> studenti){
		IscrizioneDto iscrizioneDto = new IscrizioneDto();
		Studente studente = new Studente();
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			studente = studenteIterator.next();
			if(studente.getIdstudente() == iscrizione.getStudente().getIdstudente()){
				iscrizioneDto.setIdUserStudente(studente.getUtente().getIdutente());
			}
		iscrizioneDto.setIdIscrizione(iscrizione.getIdiscrizione());
		iscrizioneDto.setIdInsegnamento(iscrizione.getInsegnamento().getIdinsegnamento());
		iscrizioneDto.setStato(iscrizione.getStato());
		}
		return iscrizioneDto;
	}
}
