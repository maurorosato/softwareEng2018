package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.StudenteDto;

public class StudenteConverter implements IConverter {
	
	public <T> Object domainToDto(T domainObject) {
	//public static StudenteDto domainToDto(Studente studente,/* List<Utente> utenti,*/ List<CorsoDiStudio> corsi,List<NumeroTelefono> numeriTelefono){
		Studente studente = (Studente) domainObject;
		StudenteDto studenteDto = new StudenteDto();
//		String numTelefono = "- ";
		
		studenteDto.setNazione(studente.getNazione());
		studenteDto.setIndirizzo(studente.getIndirizzo());
		studenteDto.setMatricola(studente.getMatricola());
		studenteDto.setIdStudente(studente.getIdstudente());
		studenteDto.setNome(studente.getUtente().getNome());
		studenteDto.setEmail(studente.getUtente().getEmail());
		studenteDto.setCognome(studente.getUtente().getNome());
		studenteDto.setCodiceFiscale(studente.getCodiceFiscale());
		studenteDto.setPassword(studente.getUtente().getPassword());
		studenteDto.setIdUserStudente(studente.getUtente().getIdutente());
		studenteDto.setDataNascita(studente.getUtente().getDataNascita());
		studenteDto.setIdCorsoDiStudio(studente.getCorsoDiStudioIdcorsoDiStudio());
		
//		Iterator<NumeroTelefono> numeroIterator = numeriTelefono.iterator();
//		while (numeroIterator.hasNext()){
//			NumeroTelefono num = numeroIterator.next();
//			if(num.getUtente().getIdutente() == studenteDto.getIdUserStudente()){
//				numTelefono = numTelefono + num.getNumeroTelefono() + "- ";
//			}
//			studenteDto.setNumeroTelefono(numTelefono);
//		}	

//		Iterator<Utente> utenteIterator = utenti.iterator();
//		while (utenteIterator.hasNext()){
//			Utente utente = utenteIterator.next();
//			if(utente.getIdutente() == studente.getUtente().getIdutente() ){
//				studenteDto.setNome(utente.getNome());
//				studenteDto.setEmail(utente.getEmail());
//				studenteDto.setCognome(utente.getCognome());
//				studenteDto.setPassword(utente.getPassword());
//				studenteDto.setNazione(studente.getNazione());
//				studenteDto.setMatricola(studente.getMatricola());
//				studenteDto.setIndirizzo(studente.getIndirizzo());
//				studenteDto.setDataNascita(utente.getDataNascita());
//				studenteDto.setIdUserStudente(utente.getIdutente());
//				studenteDto.setIdStudente(studente.getIdstudente());
//				studenteDto.setCodiceFiscale(studente.getCodiceFiscale());
//				studenteDto.setIdCorsoDiStudio(studente.getCorsoDiStudioIdcorsoDiStudio());
//			}
//			
//			Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
//			while (corsoIterator.hasNext()){
//				CorsoDiStudio c = corsoIterator.next();
//				if(c.getIdcorsoDiStudio() == studente.getCorsoDiStudioIdcorsoDiStudio()){
//					studenteDto.setCorsoDiStudio(c.getNomeCorso());
//				}
//			}
//			
		return studenteDto;
	}

	@Override
	public <T> Object dtoToDomain(T dtoObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
