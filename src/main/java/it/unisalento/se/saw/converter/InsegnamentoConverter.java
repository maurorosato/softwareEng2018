package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.InsegnamentoDto;

public class InsegnamentoConverter implements IConverter {

	public <T> Object dtoToDomain(T dtoObject) {
	//public static Insegnamento dtoToDomain(InsegnamentoDto insegnamentoDto,List<Docente> docenti, List<CorsoDiStudio> corsi){
		InsegnamentoDto insegnamentoDto = (InsegnamentoDto) dtoObject;
		Insegnamento insegnamento = new Insegnamento();
		//Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
		int anno = 0;

		insegnamento.setCfu(insegnamentoDto.getCfu());
		insegnamento.setNome(insegnamentoDto.getNome());

		//Iterator<Docente> docenteIterator = docenti.iterator();
		Docente docente = new Docente();
//
//		while (docenteIterator.hasNext()){
//			Docente docenteI = docenteIterator.next();
//			if((docenteI.getUtente().getNome()+" "+docenteI.getUtente().getCognome()).equals(insegnamentoDto.getDocente())){
//				docente.setIddocente(docenteI.getIddocente());
//				insegnamento.setDocente(docente);
//			}
//		}
//		
//		while (corsoIterator.hasNext()){
//			CorsoDiStudio corsoI = corsoIterator.next();
//			if(corsoI.getNomeCorso().equals(insegnamentoDto.getCorsoDiStudio())){
//				insegnamento.setCorsoDiStudioIdcorsoDiStudio(corsoI.getIdcorsoDiStudio());	
//			}
//		}
//		
//		switch (insegnamentoDto.getAnnoCorso()){
//			case "Primo anno":
//				anno = 1 ;
//				break;	
//			case "Secondo anno":
//				anno = 2 ;
//				break;
//			case "Terzo anno":
//				anno = 3 ;
//				break;
//		}
//		insegnamento.setAnno(anno);
		return insegnamento;
	}
	
	public <T> Object domainToDto(T domainObject) {
		Insegnamento insegnamento = (Insegnamento) domainObject;
		InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
		
		insegnamentoDto.setCfu(insegnamento.getCfu());
		insegnamentoDto.setNome(insegnamento.getNome());
		insegnamentoDto.setIdInsegnamento(insegnamento.getIdinsegnamento());
		insegnamentoDto.setIdUserDocente(insegnamento.getDocente().getUtente().getIdutente());
		insegnamentoDto.setDocente(insegnamento.getDocente().getUtente().getNome() + ' ' + insegnamento.getDocente().getUtente().getCognome());

		return insegnamentoDto;
	}
}
