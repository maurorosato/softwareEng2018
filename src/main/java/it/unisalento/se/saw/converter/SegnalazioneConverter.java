package it.unisalento.se.saw.converter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.dto.SegnalazioneDto;

public class SegnalazioneConverter {

	public static SegnalazioneDto domainToDto(Segnalazione segnalazione/*, List<Aula> aule, List<Docente> docenti */) {
		SegnalazioneDto segnalazioneDto = new SegnalazioneDto();
		segnalazioneDto.setData(segnalazione.getData());
		segnalazioneDto.setNomeAula(segnalazione.getAula().getNome());
		segnalazioneDto.setDescrizione(segnalazione.getDescrizione());
		segnalazioneDto.setMotivazione(segnalazione.getMotivazione());
		segnalazioneDto.setIdSegnalazione(segnalazione.getIdsegnalazione());
		segnalazioneDto.setStatoSegnalazione(segnalazione.getStatoSegnalazione());
		segnalazioneDto.setOggettoInteressato(segnalazione.getOggettoInteressato());
		segnalazioneDto.setIdDocenteSegnalante(segnalazione.getDocente().getIddocente());
		segnalazioneDto.setIdUserDocenteSegnalante(segnalazione.getDocente().getUtente().getIdutente());
		segnalazioneDto.setDocenteSegnalante(segnalazione.getDocente().getUtente().getNome() + ' ' +segnalazione.getDocente().getUtente().getCognome());
		
		switch (segnalazioneDto.getOggettoInteressato()) {
		case "proiettore":
			segnalazioneDto.setImage("strumentazione.png");
			break;
		case "wifi":
			segnalazioneDto.setImage("wifi.jpg");
			break;
		case "aula":
			segnalazioneDto.setImage("aula.jpg");
			break;
		case "riscaldamento":
			segnalazioneDto.setImage("riscaldamento.png");
			break;
		default:
			segnalazioneDto.setImage("default.png");
			break;
		}
//		String nomeAula = null;
//		String docenteSegnalante = null;
//		Iterator<Aula> aulaIterator = aule.iterator();
//		Iterator<Docente> docenteIterator = docenti.iterator();		
//		while (docenteIterator.hasNext()){
//			Docente docente = docenteIterator.next();
//			if(segnalazione.getDocente().getIddocente() == docente.getIddocente()){
//				docenteSegnalante = docente.getUtente().getNome() + ' ' + docente.getUtente().getCognome();
//				segnalazioneDto.setIdUserDocenteSegnalante(docente.getUtente().getIdutente());
//			}
//		}
//
//		while (aulaIterator.hasNext()){
//			Aula aula = aulaIterator.next();
//			if (segnalazione.getAula().getIdaula() == aula.getIdaula()){
//				nomeAula = aula.getNome();
//			}
//		}
		
//		segnalazioneDto.setIdSegnalazione(segnalazione.getIdsegnalazione());
//		segnalazioneDto.setOggettoInteressato(segnalazione.getOggettoInteressato());
//		segnalazioneDto.setMotivazione(segnalazione.getMotivazione());
//		segnalazioneDto.setStatoSegnalazione(segnalazione.getStatoSegnalazione());
//		segnalazioneDto.setNomeAula(nomeAula);
//		segnalazioneDto.setDescrizione(segnalazione.getDescrizione());
//		segnalazioneDto.setDocenteSegnalante(docenteSegnalante);
//		segnalazioneDto.setData(segnalazione.getData());
		
		return segnalazioneDto;
	}
	
	public static Segnalazione dtoToDomain(SegnalazioneDto segnalazioneDto, List<Aula> aule, Date data ) {
		Segnalazione segnalazione = new Segnalazione();
		Aula aula = new Aula();
		
		if(segnalazioneDto.getOggettoInteressato().equals("altro") && segnalazioneDto.getNomeAula().equals("altro")){
			aula.setIdaula(1);
			segnalazione.setAula(aula);
		}
		else{
			Iterator<Aula> aulaIterator = aule.iterator();		
			while(aulaIterator.hasNext()){
				Aula aulaList = aulaIterator.next();
				if ((aulaList.getNome()).equals(segnalazioneDto.getNomeAula())){
					aula.setIdaula(aulaList.getIdaula());
					segnalazione.setAula(aula);
				}
			}
		}
		Docente docente = new Docente();
		docente.setIddocente(segnalazioneDto.getIdDocenteSegnalante());
		
		segnalazione.setData(data);
		segnalazione.setDocente(docente);
		segnalazione.setStatoSegnalazione("attivo");
		segnalazione.setMotivazione(segnalazioneDto.getMotivazione());
		segnalazione.setDescrizione("La segnalazione è stata presa in carico.");
		segnalazione.setOggettoInteressato(segnalazioneDto.getOggettoInteressato());

		return segnalazione;
	}
}
