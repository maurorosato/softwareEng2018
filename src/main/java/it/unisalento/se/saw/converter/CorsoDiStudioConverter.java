package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.dto.CorsoDiStudioDto;

public class CorsoDiStudioConverter implements IConverter {
	
	public <T> Object domainToDto(T domainObject) {
		CorsoDiStudioDto corsoDto = new CorsoDiStudioDto();
		CorsoDiStudio corso = (CorsoDiStudio) domainObject;
		corsoDto.setIdcorsoDiStudio(corso.getIdcorsoDiStudio());
        corsoDto.setNomeCorso(corso.getNomeCorso());
        corsoDto.setDipartimento(corso.getDipartimento());
        
        return corsoDto;
    }
	
	public <T> Object dtoToDomain(T dtoObject) {

		CorsoDiStudio corso = new CorsoDiStudio();
		CorsoDiStudioDto corsoDto = (CorsoDiStudioDto) dtoObject;
		corso.setNomeCorso(corsoDto.getNomeCorso());
		corso.setDipartimento(corsoDto.getDipartimento());
		
		return corso;
	}
}
