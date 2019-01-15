package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;

public class DocenteConverter {
	public static DocenteDto domainToDto(Docente docente, List<Utente> utenti){
		DocenteDto dDto = new DocenteDto();
		
		Iterator<Utente> utenteIterator = utenti.iterator();
		while (utenteIterator.hasNext()){
			Utente u = utenteIterator.next();
			if(docente.getUtente().getIdutente() == u.getIdutente() ){

				dDto.setNome(u.getNome());
				dDto.setCognome(u.getCognome());
				dDto.setEmail(u.getEmail());
				dDto.setGrado(docente.getGrado());
				dDto.setIdDocente(docente.getIddocente());
			}
		}
		return dDto;
	}
}
