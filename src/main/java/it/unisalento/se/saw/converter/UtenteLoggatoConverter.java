package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.dto.UtenteDto;
import it.unisalento.se.saw.dto.UtenteLoggatoDto;

public class UtenteLoggatoConverter {
	public static UtenteLoggatoDto domainToDto(UtenteDto uDto,List<Utente> utenti, List<Studente> studenti,List<Docente> docenti, List<CorsoDiStudio> corsi){
		UtenteLoggatoDto uLoggatoDto = new UtenteLoggatoDto();
		DocenteDto docenteDto = new DocenteDto();
		StudenteDto studenteDto;

		uLoggatoDto.setUtenteDto(uDto);
		uLoggatoDto.setTypeUtente("segreteria");
		
		Iterator<Docente> docenteIterator = docenti.iterator();
		while(docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			if (docente.getUtente().getIdutente() == uDto.getIdutente()){
				docenteDto= DocenteConverter.domainToDto(docente, utenti);
				uLoggatoDto.setDocenteDto(docenteDto);;
				uLoggatoDto.setTypeUtente("docente");
			}
		}
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			Studente studente = studenteIterator.next();
			if (studente.getUtente().getIdutente() == uDto.getIdutente()){
				studenteDto = StudenteConverter.domainToDto(studente, utenti,corsi);
				uLoggatoDto.setStudenteDto(studenteDto);
				uLoggatoDto.setTypeUtente("studente");
			}
		}
		
		return uLoggatoDto;
	}
}
