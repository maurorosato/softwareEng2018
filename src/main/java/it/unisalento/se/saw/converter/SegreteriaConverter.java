package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.SegreteriaDidattica;
import it.unisalento.se.saw.dto.SegreteriaDto;

public class SegreteriaConverter implements IConverter {
	
	public <T> Object domainToDto(T domainObject) {
		SegreteriaDidattica segreteria = (SegreteriaDidattica) domainObject;
		SegreteriaDto segreteriaDto = new SegreteriaDto();
		
		segreteriaDto.setIdUserSegretario(segreteria.getUtente().getIdutente());
		segreteriaDto.setRuolo(segreteria.getRuolo());
		segreteriaDto.setStipendio(segreteria.getStipendio());
				
		return segreteriaDto;
	}

	@Override
	public <T> Object dtoToDomain(T dtoObject) {
		SegreteriaDto segreteriaDto = (SegreteriaDto) dtoObject;
		SegreteriaDidattica segreteria = new SegreteriaDidattica();
		segreteria.setIdsegreteriaDidattica(segreteriaDto.getIdUserSegretario());
		segreteria.setRuolo(segreteriaDto.getRuolo());
		segreteria.setStipendio(segreteriaDto.getStipendio());
		
		return segreteria;
	}
}
