package it.unisalento.se.saw.converter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.dto.SegnalazioneDto;

public class SegnalazioneConverter implements IConverter {
	
	public <T> Object domainToDto(T domainObject) {
		Segnalazione segnalazione = (Segnalazione) domainObject;
		
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
		return segnalazioneDto;
	}
	public <T> Object dtoToDomain(T dtoObject) {
		SegnalazioneDto segnalazioneDto = (SegnalazioneDto) dtoObject;
		Segnalazione segnalazione = new Segnalazione();

		segnalazione.setMotivazione(segnalazioneDto.getMotivazione());
		segnalazione.setOggettoInteressato(segnalazioneDto.getOggettoInteressato());

		return segnalazione;
	}

}
