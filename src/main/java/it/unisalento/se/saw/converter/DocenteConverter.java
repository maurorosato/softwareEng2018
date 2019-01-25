package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;

public class DocenteConverter {
	public static DocenteDto domainToDto(Docente docente,/*,List<Utente> utenti,*/List<NumeroTelefono> numeriTelefono){
		DocenteDto dDto = new DocenteDto();
		String numTelefono = "- ";
		
		dDto.setGrado(docente.getGrado());
		dDto.setStipendio(docente.getStipendio());
		dDto.setIdDocente(docente.getIddocente());
		dDto.setNome(docente.getUtente().getNome());
		dDto.setEmail(docente.getUtente().getEmail());
		dDto.setCognome(docente.getUtente().getCognome());
		dDto.setPassword(docente.getUtente().getPassword());
		dDto.setIdUserDocente(docente.getUtente().getIdutente());
		dDto.setDataNascita(docente.getUtente().getDataNascita());	
/*		
		Iterator<Utente> utenteIterator = utenti.iterator();
		while (utenteIterator.hasNext()){
			Utente u = utenteIterator.next();
			
			if(docente.getUtente().getIdutente() == u.getIdutente()){
				dDto.setNome(u.getNome());
				dDto.setEmail(u.getEmail());
				dDto.setCognome(u.getCognome());
				dDto.setGrado(docente.getGrado());
				dDto.setPassword(u.getPassword());
				dDto.setIdUserDocente(u.getIdutente());
				dDto.setDataNascita(u.getDataNascita());
				dDto.setStipendio(docente.getStipendio());
				dDto.setIdDocente(docente.getIddocente());
			}
		}
*/		
		Iterator<NumeroTelefono> numeroIterator = numeriTelefono.iterator();
		while (numeroIterator.hasNext()){
			NumeroTelefono num = numeroIterator.next();
			if(num.getUtente().getIdutente() == dDto.getIdUserDocente()){
				numTelefono = numTelefono + num.getNumeroTelefono();
			}
			dDto.setNumeroTelefono(numTelefono);
		}	

		return dDto;
	}
}
