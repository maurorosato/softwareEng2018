package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.dto.AulaDto;

public class AulaConverter implements IConverter {
	public <T> Object domainToDto(T domainObject) {
		AulaDto aulaDto = new AulaDto();
		Aula aula = (Aula) domainObject;
		aulaDto.setIdaula(aula.getIdaula());
		aulaDto.setEdificio(aula.getEdificio());
		aulaDto.setNome(aula.getNome());
		aulaDto.setCapienza(aula.getCapienza());
		aulaDto.setStato(aula.getStato());
		aulaDto.setLatitudine(aula.getLatitudine());
		aulaDto.setLongitudine(aula.getLongitudine());
		if(aula.getWifi()==0)
			aulaDto.setWifi(false);
		else
			aulaDto.setWifi(true);
		
		return aulaDto;
	}
	public <T> Object dtoToDomain(T dtoObject) {
		AulaDto aulaDto = (AulaDto) dtoObject;
		Aula aula = new Aula();
		aula.setNome(aulaDto.getNome());
		aula.setEdificio(aulaDto.getEdificio());
		aula.setStato(aulaDto.getStato());
		aula.setCapienza(aulaDto.getCapienza());
		aula.setLatitudine(aulaDto.getLatitudine());
		aula.setLongitudine(aulaDto.getLongitudine());
		if(aulaDto.isWifi())
			aula.setWifi((byte) 1);
		else
			aula.setWifi((byte) 0);
		
		return aula;	
	}
}
