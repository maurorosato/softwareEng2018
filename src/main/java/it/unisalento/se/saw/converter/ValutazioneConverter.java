package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.domain.Valutazione;
import it.unisalento.se.saw.dto.ValutazioneDto;

public class ValutazioneConverter implements IConverter{
	public <T> Object dtoToDomain(T dtoObject) {
	//public static Valutazione DtoToDomain (ValutazioneDto valutazioneDto,List<Studente> studenti){
		ValutazioneDto valutazioneDto = (ValutazioneDto) dtoObject;
		
		Lezione lezione = new Lezione();
//		Studente studente = new Studente();
		Valutazione valutazione = new Valutazione();
		
		lezione.setIdlezione(valutazioneDto.getIdLezione());
		
//		Iterator<Studente> studenteIterator = studenti.iterator();
//		while(studenteIterator.hasNext()){
//			Studente student = studenteIterator.next();
//			if(student.getUtente().getIdutente() == valutazioneDto.getIdStudente()){
//				studente.setIdstudente(student.getIdstudente());
//			}
//		}
		//studente.setIdstudente(valutazioneDto.getIdStudente());
		valutazione.setLezione(lezione);
//		valutazione.setStudente(studente);
		valutazione.setNota(valutazioneDto.getNota());
		valutazione.setValutazione(valutazioneDto.getValutazione());
		valutazione.setMaterialeDidatticoIdmaterialeDidattico(valutazioneDto.getIdMaterialeDidattico());
		
		return valutazione;
	}
	
	public <T> Object domainToDto(T domainObject) {
	//public static ValutazioneDto domainToDto (Valutazione valutazione){
		Valutazione valutazione = (Valutazione) domainObject;
		ValutazioneDto valutazioneDto = new ValutazioneDto();
		
		valutazioneDto.setIdValutazione(valutazione.getIdvalutazione());
		valutazioneDto.setIdLezione(valutazione.getLezione().getIdlezione());
		valutazioneDto.setNota(valutazione.getNota());
		valutazioneDto.setValutazione(valutazione.getValutazione());
		valutazioneDto.setIdStudente(valutazione.getStudente().getIdstudente());
		valutazioneDto.setIdMaterialeDidattico(valutazione.getMaterialeDidatticoIdmaterialeDidattico());
		
		return valutazioneDto;
	}
}
