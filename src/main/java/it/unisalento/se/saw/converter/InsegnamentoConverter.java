package it.unisalento.se.saw.converter;

import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.InsegnamentoDto;

public class InsegnamentoConverter {

	public static Insegnamento dtoToDomain(InsegnamentoDto insegnamentoDto,List<Docente> docenti, List<CorsoDiStudio> corsi){
		Insegnamento insegnamento = new Insegnamento();
		Iterator<Docente> docenteIterator = docenti.iterator();
		Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
		Docente docente = new Docente();
		int anno = 0;

		while (docenteIterator.hasNext()){
			Docente docenteI = docenteIterator.next();
			if((docenteI.getUtente().getNome()+" "+docenteI.getUtente().getCognome()).equals(insegnamentoDto.getDocente())){
				docente.setIddocente(docenteI.getIddocente());
				insegnamento.setDocente(docente);
			}
		}
		
		while (corsoIterator.hasNext()){
			CorsoDiStudio corsoI = corsoIterator.next();
			if(corsoI.getNomeCorso().equals(insegnamentoDto.getCorsoDiStudio())){
				insegnamento.setCorsoDiStudioIdcorsoDiStudio(corsoI.getIdcorsoDiStudio());	
			}
		}
		
		insegnamento.setNome(insegnamentoDto.getNome());
		insegnamento.setCfu(insegnamentoDto.getCfu());

		switch (insegnamentoDto.getAnnoCorso()){
			case "Primo anno":
				anno = 1 ;
				break;	
			case "Secondo anno":
				anno = 2 ;
				break;
			case "Terzo anno":
				anno = 3 ;
				break;
		}
		insegnamento.setAnno(anno);
		return insegnamento;
	}
	
	public static InsegnamentoDto domainToDto(){
		InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
		
		return insegnamentoDto;
	}
}
