package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.dto.CorsoDiStudioDto;

public class CorsoDiStudioConverter {

	public static CorsoDiStudioDto domainToDto(CorsoDiStudio corso) {
		CorsoDiStudioDto corsoDto = new CorsoDiStudioDto();
        
		corsoDto.setIdcorsoDiStudio(corso.getIdcorsoDiStudio());
        corsoDto.setNomeCorso(corso.getNomeCorso());
        corsoDto.setDipartimento(corso.getDipartimento());
        
        return corsoDto;
    }
	
	public static CorsoDiStudio dtoToDomain(CorsoDiStudioDto corsoDto){
		CorsoDiStudio corso = new CorsoDiStudio();
		
		corso.setNomeCorso(corsoDto.getNomeCorso());
		corso.setDipartimento(corsoDto.getDipartimento());
		
		return corso;
	}
}
