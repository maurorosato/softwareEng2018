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
		Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
		int anno = 0;

		insegnamento.setCfu(insegnamentoDto.getCfu());
		insegnamento.setNome(insegnamentoDto.getNome());

		Iterator<Docente> docenteIterator = docenti.iterator();
		Docente docente = new Docente();

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
	
	public static InsegnamentoDto domainToDto(Insegnamento insegnamento, List<CorsoDiStudio> corsi/*, List<Docente> docenti*/){
		InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
		
		insegnamentoDto.setCfu(insegnamento.getCfu());
		insegnamentoDto.setNome(insegnamento.getNome());
		insegnamentoDto.setIdInsegnamento(insegnamento.getIdinsegnamento());
		insegnamentoDto.setIdUserDocente(insegnamento.getDocente().getUtente().getIdutente());
		insegnamentoDto.setDocente(insegnamento.getDocente().getUtente().getNome() + ' ' + insegnamento.getDocente().getUtente().getCognome());

//
//		Iterator<Docente> docenteIterator = docenti.iterator();
//		while(docenteIterator.hasNext()){
//			Docente docente = docenteIterator.next();
//			if (insegnamento.getDocente().getIddocente() ==docente.getIddocente()){
//				insegnamentoDto.setDocente(docente.getUtente().getNome() + ' ' + docente.getUtente().getCognome());
//				insegnamentoDto.setIdUserDocente(docente.getUtente().getIdutente());
//
//			}
//		}
//		
		Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
		while(corsoIterator.hasNext()){
			CorsoDiStudio corso = corsoIterator.next();
			if (insegnamento.getCorsoDiStudioIdcorsoDiStudio() == corso.getIdcorsoDiStudio()){
				insegnamentoDto.setCorsoDiStudio(corso.getNomeCorso());
			}
		}
		switch (insegnamento.getAnno()){
		case 1:
			insegnamentoDto.setAnnoCorso("Primo Anno");
			break;
		case 2:
			insegnamentoDto.setAnnoCorso("Secondo Anno");
			break;
		case 3:
			insegnamentoDto.setAnnoCorso("Terzo Anno");
			break;
		}
		return insegnamentoDto;
	}
}
