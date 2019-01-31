package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.dto.MaterialeDidatticoDto;

public class MaterialeDidatticoConverter implements IConverter{
	@Override
	public <T> Object dtoToDomain(T domainObject) {
		MaterialeDidatticoDto materialeDidatticoDto = (MaterialeDidatticoDto) domainObject;
//	public static MaterialeDidattico dtoToDomain (MaterialeDidatticoDto materialeDidatticoDto, List<Lezione> lezioni,List<Docente> docenti){
		MaterialeDidattico materialeDidattico = new MaterialeDidattico();
		materialeDidattico.setNome(materialeDidatticoDto.getNome());
		materialeDidattico.setLink(materialeDidatticoDto.getLink());
		
//		Docente docente = new Docente();
//		Lezione lezione = new Lezione();
//		docente.setIddocente(materialeDidatticoDto.getIdUtente());
		
//		Iterator<Lezione> lezioneIterator = lezioni.iterator();
//		while (lezioneIterator.hasNext()){
//			Lezione lez = lezioneIterator.next();
//			if (lez.getEvento().getIdevento() == materialeDidatticoDto.getIdEvento()){
//				lezione.setIdlezione(lez.getIdlezione());
//			}
//		}
//		
//		Iterator<Docente> docenteIterator = docenti.iterator();
//		while (docenteIterator.hasNext()){
//			Docente doc = docenteIterator.next();
//			if (materialeDidatticoDto.getIdUtente() == doc.getUtente().getIdutente()){
//				docente.setIddocente(doc.getIddocente());
//			}
//		}
//		

//		materialeDidattico.setDocente(docente);
//		materialeDidattico.setLezione(lezione);
		
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
