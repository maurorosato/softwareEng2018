package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.dto.AulaDto;

public class AulaConverter {
	
	public static AulaDto domainToDto(Aula aula) {
		AulaDto aulaDto = new AulaDto();

		aulaDto.setIdaula(aula.getIdaula());
		aulaDto.setEdificio(aula.getEdificio());
		aulaDto.setNome(aula.getNome());
		aulaDto.setCapienza(aula.getCapienza());
		aulaDto.setStato(aula.getStato());
		if(aula.getWifi()==0)
			aulaDto.setWifi(false);
		else
			aulaDto.setWifi(true);
		
		return aulaDto;
	}
	
	public static Aula dtoToDomain(AulaDto aulaDto) {
		Aula aula = new Aula();
		aula.setNome(aulaDto.getNome());
		aula.setEdificio(aulaDto.getEdificio());
		aula.setStato(aulaDto.getStato());
		aula.setCapienza(aulaDto.getCapienza());
		if(aulaDto.isWifi())
			aula.setWifi((byte) 1);
		else
			aula.setWifi((byte) 0);
		
		return aula;
		
	}
}
