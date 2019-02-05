package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.dto.MaterialeDidatticoDto;

public class MaterialeDidatticoConverter implements IConverter{
	@Override
	public <T> Object dtoToDomain(T domainObject) {
		MaterialeDidatticoDto materialeDidatticoDto = (MaterialeDidatticoDto) domainObject;
		MaterialeDidattico materialeDidattico = new MaterialeDidattico();
		materialeDidattico.setNome(materialeDidatticoDto.getNome());
		materialeDidattico.setLink(materialeDidatticoDto.getLink());
			
		return materialeDidattico;
	}
	
	public <T> Object domainToDto(T domainObject) {
		MaterialeDidattico materialeDidattico = (MaterialeDidattico) domainObject;
		MaterialeDidatticoDto materialeDidatticoDto = new MaterialeDidatticoDto();

		materialeDidatticoDto.setNome(materialeDidattico.getNome());
		materialeDidatticoDto.setLink(materialeDidattico.getLink());
		materialeDidatticoDto.setIdDocente(materialeDidattico.getDocente().getIddocente());
		materialeDidatticoDto.setIdMaterialeDidattico(materialeDidattico.getIdmaterialeDidattico());
		materialeDidatticoDto.setIdEvento(materialeDidattico.getLezione().getEvento().getIdevento());
		materialeDidatticoDto.setIdUtente(materialeDidattico.getDocente().getUtente().getIdutente());

		return materialeDidatticoDto;
	}


}
