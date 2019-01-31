package it.unisalento.se.saw.converter;


import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.UtenteDto;


public class UtenteConverter implements IConverter {
	
	//public static UtenteDto domainToDto(Utente utente){
	public <T> Object domainToDto(T domainObject) {
		Utente utente = (Utente) domainObject;
		UtenteDto uDto = new UtenteDto();
		
		uDto.setIdutente(utente.getIdutente());
		uDto.setNome(utente.getNome());
		uDto.setCognome(utente.getCognome());
		uDto.setEmail(utente.getEmail());
		uDto.setPassword(utente.getPassword());
		
		return uDto;
	}

	@Override
	public <T> Object dtoToDomain(T dtoObject) {
		return dtoObject;
	}
}
