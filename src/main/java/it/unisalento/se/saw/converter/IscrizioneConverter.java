package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.IscrizioneDto;

public class IscrizioneConverter implements IConverter {
	public <T> Object dtoToDomain(T dtoObject) {
	//public static Iscrizione DtoToDomain(IscrizioneDto iscrizioneDto, List<Studente> studenti){
		IscrizioneDto iscrizioneDto = (IscrizioneDto) dtoObject;
		//Studente studente = new Studente();
		//Studente student = new Studente();
		Iscrizione iscrizione = new Iscrizione();
		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setIdinsegnamento(iscrizioneDto.getIdInsegnamento());

		
//		Iterator<Studente> studenteIterator = studenti.iterator();
//		while(studenteIterator.hasNext()){
//			student = studenteIterator.next();
//			if(student.getUtente().getIdutente() == iscrizioneDto.getIdUserStudente()){
//				studente.setIdstudente(student.getIdstudente());
//			}
//		}
		
		iscrizione.setInsegnamento(insegnamento);
		//iscrizione.setStudente(studente);
		iscrizione.setStato(iscrizioneDto.getStato());
		
		return iscrizione;
	}
	public <T> Object domainToDto(T domainObject) {
		IscrizioneDto iscrizioneDto = new IscrizioneDto();
		Iscrizione iscrizione = (Iscrizione) domainObject;
		
		iscrizioneDto.setStato(iscrizione.getStato());
		iscrizioneDto.setIdIscrizione(iscrizione.getIdiscrizione());
		iscrizioneDto.setIdInsegnamento(iscrizione.getInsegnamento().getIdinsegnamento());
		iscrizioneDto.setIdUserStudente(iscrizione.getStudente().getUtente().getIdutente());
		return iscrizioneDto;
	}
}
