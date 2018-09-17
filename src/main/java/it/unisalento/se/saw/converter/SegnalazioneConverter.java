package it.unisalento.se.saw.converter;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.SegnalazioneDto;

public class SegnalazioneConverter {

	public static SegnalazioneDto domainToDto(Segnalazione segnalazione, List<Aula> aule ) {
		SegnalazioneDto segnalazioneDto = new SegnalazioneDto();
		String nomeAula = null;
		Iterator<Aula> aulaIterator = aule.iterator();

		while (aulaIterator.hasNext()){
			Aula aula = aulaIterator.next();

			if (segnalazione.getAula().getIdaula() == aula.getIdaula()){
				nomeAula = aula.getNome();
				}
		}
		segnalazioneDto.setIdSegnalazione(segnalazione.getIdsegnalazione());
		segnalazioneDto.setOggettoInteressato(segnalazione.getOggettoInteressato());
		segnalazioneDto.setMotivazione(segnalazione.getMotivazione());
		segnalazioneDto.setStatoSegnalazione(segnalazione.getStatoSegnalazione());
		segnalazioneDto.setNomeAula(nomeAula);
		segnalazioneDto.setData((Date) segnalazione.getData());
				
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
		return segnalazioneDto;
	}
	
	public static Segnalazione dtoToDomain(SegnalazioneDto segnalazioneDto, List<Aula> aule, int idUtente, Date data ) {
		Segnalazione segnalazione = new Segnalazione();
		Iterator<Aula> aulaIterator = aule.iterator();
		Aula aula = new Aula();
		Utente utente = new Utente();
		Docente docente = new Docente();
		
		while(aulaIterator.hasNext()){
			Aula aulaList = aulaIterator.next();
			if ((aulaList.getNome()).equals(segnalazioneDto.getNomeAula())){
				aula.setIdaula(aulaList.getIdaula());
				}
		}
		
		utente.setIdutente(idUtente);
		docente.setUtente(utente);
		
		segnalazione.setOggettoInteressato(segnalazioneDto.getOggettoInteressato());
		segnalazione.setMotivazione(segnalazioneDto.getMotivazione());
		segnalazione.setAula(aula);
		segnalazione.setDocente(docente);
		segnalazione.setStatoSegnalazione("attivo");
		segnalazione.setData(data);
		
		return segnalazione;
	}
}
