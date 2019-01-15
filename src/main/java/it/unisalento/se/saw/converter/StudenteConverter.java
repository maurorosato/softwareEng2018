package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.StudenteDto;

public class StudenteConverter {
	public static StudenteDto domainToDto(Studente studente, List<Utente> utenti, List<CorsoDiStudio> corsi){
		StudenteDto studenteDto = new StudenteDto();
		
		Iterator<Utente> utenteIterator = utenti.iterator();
		while (utenteIterator.hasNext()){
			Utente utente = utenteIterator.next();
			if(utente.getIdutente() == studente.getUtente().getIdutente() ){
				
				studenteDto.setNome(utente.getNome());
				studenteDto.setCognome(utente.getCognome());
				studenteDto.setIdUserStudente(utente.getIdutente());
				studenteDto.setIdStudente(studente.getIdstudente());
				studenteDto.setMatricola(studente.getMatricola());
				studenteDto.setEmail(utente.getEmail());
				studenteDto.setCodiceFiscale(studente.getCodiceFiscale());
				studenteDto.setPassword(utente.getPassword());
				studenteDto.setNazione(studente.getNazione());
				studenteDto.setIndirizzo(studente.getIndirizzo());
				studenteDto.setDataNascita(utente.getDataNascita());
				studenteDto.setIdCorsoDiStudio(studente.getCorsoDiStudioIdcorsoDiStudio());
			}
			
			Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
			while (corsoIterator.hasNext()){
				CorsoDiStudio c = corsoIterator.next();
				if(c.getIdcorsoDiStudio() == studente.getCorsoDiStudioIdcorsoDiStudio()){
					studenteDto.setCorsoDiStudio(c.getNomeCorso());
				}
			}
		}
		return studenteDto;
	}
}
