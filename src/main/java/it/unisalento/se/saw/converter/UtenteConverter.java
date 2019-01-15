package it.unisalento.se.saw.converter;


import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.UtenteDto;


public class UtenteConverter {
	public static UtenteDto domainToDto(Utente utente){
		
		UtenteDto uDto = new UtenteDto();
		
		uDto.setIdutente(utente.getIdutente());
		uDto.setNome(utente.getNome());
		uDto.setCognome(utente.getCognome());
		uDto.setEmail(utente.getEmail());
		uDto.setPassword(utente.getPassword());
		
		return uDto;
	}
}
