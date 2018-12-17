package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.dto.MaterialeDidatticoDto;

public class MaterialeDidatticoConverter {

	public static MaterialeDidattico dtoToDomain (MaterialeDidatticoDto materialeDidatticoDto, List<Lezione> lezioni,List<Docente> docenti){
		MaterialeDidattico materialeDidattico = new MaterialeDidattico();

		Docente docente = new Docente();
		Lezione lezione = new Lezione();
		docente.setIddocente(materialeDidatticoDto.getIdUtente());
		
		Iterator<Lezione> lezioneIterator = lezioni.iterator();
		while (lezioneIterator.hasNext()){
			Lezione lez = lezioneIterator.next();
			if (lez.getEvento().getIdevento() == materialeDidatticoDto.getIdEvento()){
				lezione.setIdlezione(lez.getIdlezione());
			}
		}
		
		Iterator<Docente> docenteIterator = docenti.iterator();
		while (docenteIterator.hasNext()){
			Docente doc = docenteIterator.next();
			if (materialeDidatticoDto.getIdUtente() == doc.getUtente().getIdutente()){
				docente.setIddocente(doc.getIddocente());
			}
		}
		
		materialeDidattico.setNome(materialeDidatticoDto.getNome());
		materialeDidattico.setLink(materialeDidatticoDto.getLink());
		materialeDidattico.setGradimento(materialeDidatticoDto.getGradimento());
		materialeDidattico.setDocente(docente);
		materialeDidattico.setLezione(lezione);
		
		return materialeDidattico;
	}
	
	public static MaterialeDidatticoDto domainToDto(MaterialeDidattico materialeDidattico, List<Lezione> lezioni,List<Docente> docenti){
		MaterialeDidatticoDto materialeDidatticoDto = new MaterialeDidatticoDto();
		
		Iterator<Lezione> lezioneIterator = lezioni.iterator();
		while (lezioneIterator.hasNext()){
			Lezione lez = lezioneIterator.next();
			if (lez.getIdlezione() == materialeDidattico.getLezione().getIdlezione()){
				materialeDidatticoDto.setIdEvento(lez.getEvento().getIdevento());
			}
		}
		
		Iterator<Docente> docenteIterator = docenti.iterator();
		while (docenteIterator.hasNext()){
			Docente doc = docenteIterator.next();
			if (doc.getIddocente() == materialeDidattico.getDocente().getIddocente()){
				materialeDidatticoDto.setIdUtente(doc.getUtente().getIdutente());
			}
		}

		materialeDidatticoDto.setNome(materialeDidattico.getNome());
		materialeDidatticoDto.setLink(materialeDidattico.getLink());
		materialeDidatticoDto.setGradimento(materialeDidattico.getGradimento());
		materialeDidatticoDto.setIdDocente(materialeDidattico.getDocente().getIddocente());
		//materialeDidatticoDto.setIdUtente(materialeDidattico.getDocente().getIddocente());
		materialeDidatticoDto.setIdMaterialeDidattico(materialeDidattico.getIdmaterialeDidattico());
		
		return materialeDidatticoDto;
	}
}
