package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.dto.UtenteDto;
import it.unisalento.se.saw.dto.UtenteLoggatoDto;

public class UtenteLoggatoConverter {
	
	public static UtenteLoggatoDto domainToDto(UtenteDto uDto,List<Utente> utenti, List<Studente> studenti,List<Docente> docenti, List<CorsoDiStudio> corsi,List<NumeroTelefono> numeriTelefono){
		UtenteLoggatoDto uLoggatoDto = new UtenteLoggatoDto();
		IConverter docenteConverter = new DocenteConverter();
		IConverter studenteConverter = new StudenteConverter();

		DocenteDto docenteDto = new DocenteDto();
		StudenteDto studenteDto;
		String numTelefono = "- ";
		uLoggatoDto.setUtenteDto(uDto);
		uLoggatoDto.setTypeUtente("segreteria");
		
		Iterator<Docente> docenteIterator = docenti.iterator();
		while(docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			if (docente.getUtente().getIdutente() == uDto.getIdutente()){
				docenteDto = (DocenteDto) docenteConverter.domainToDto(docente);
				//docenteDto= DocenteConverter.domainToDto(docente,/*,utenti,*/numeriTelefono);
				uLoggatoDto.setDocenteDto(docenteDto);;
				uLoggatoDto.setTypeUtente("docente");
			}
		}
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			Studente studente = studenteIterator.next();
			if (studente.getUtente().getIdutente() == uDto.getIdutente()){
//				studenteDto = StudenteConverter.domainToDto(studente, /*utenti,*/corsi,numeriTelefono);
				studenteDto = (StudenteDto) studenteConverter.domainToDto(studente);
				
				Iterator<NumeroTelefono> numeroIterator = numeriTelefono.iterator();
				while (numeroIterator.hasNext()){
					NumeroTelefono num = numeroIterator.next();
					if(num.getUtente().getIdutente() == studenteDto.getIdUserStudente()){
						numTelefono = numTelefono + num.getNumeroTelefono() + "- ";
					}
					studenteDto.setNumeroTelefono(numTelefono);
				}	
				
				Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
				while (corsoIterator.hasNext()){
					CorsoDiStudio c = corsoIterator.next();
					if(c.getIdcorsoDiStudio() == studente.getCorsoDiStudioIdcorsoDiStudio()){
						studenteDto.setCorsoDiStudio(c.getNomeCorso());
					}
				}
				uLoggatoDto.setStudenteDto(studenteDto);
				uLoggatoDto.setTypeUtente("studente");
			}
		}
		
		return uLoggatoDto;
	}
}
