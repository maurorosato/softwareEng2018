package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;

public class DocenteConverter implements IConverter {
	
	public <T> Object domainToDto(T domainObject) {
	//public static DocenteDto domainToDto(Docente docente,/*,List<Utente> utenti,*/){
		DocenteDto dDto = new DocenteDto();
		Docente docente = (Docente) domainObject;
		//String numTelefono = "- ";
		dDto.setGrado(docente.getGrado());
		dDto.setStipendio(docente.getStipendio());
		dDto.setIdDocente(docente.getIddocente());
		dDto.setNome(docente.getUtente().getNome());
		dDto.setEmail(docente.getUtente().getEmail());
		dDto.setCognome(docente.getUtente().getCognome());
		dDto.setPassword(docente.getUtente().getPassword());
		dDto.setIdUserDocente(docente.getUtente().getIdutente());
		dDto.setDataNascita(docente.getUtente().getDataNascita());	

		return dDto;
	}
	
	public <T> Object dtoToDomain(T dtoObject) {
		return dtoObject;	
	}
}
