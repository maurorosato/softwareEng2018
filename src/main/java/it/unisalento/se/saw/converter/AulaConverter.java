package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.dto.AulaDto;

public class AulaConverter {
	
	public static AulaDto domainToDto(Aula aula) {
		AulaDto aulaDto = new AulaDto();

		aulaDto.setIdAula(aula.getIdaula());
		aulaDto.setEdificio(aula.getEdificio());
		aulaDto.setNome(aula.getNome());
		aulaDto.setCapienza(aula.getCapienza());
		aulaDto.setStato(aula.getStato());
		
		return aulaDto;
	}
	
	public static Aula dtoToDomain(AulaDto aulaDto) {
		Aula aula = new Aula();
		aula.setNome(aulaDto.getNome());
		aula.setEdificio(aulaDto.getEdificio());
		aula.setStato(aulaDto.getStato());
		aula.setCapienza(aulaDto.getCapienza());
		
		return aula;
	}
}
