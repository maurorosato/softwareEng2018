package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.LezioneDto;

public class LezioneConverter implements IConverter{
	public <T> Object domainToDto(T domainObject) {
		Lezione lezione = (Lezione) domainObject;
		LezioneDto lezioneDto = new LezioneDto();
		
		lezioneDto.setIdLezione(lezione.getIdlezione());
		lezioneDto.setAula(lezione.getEvento().getAula().getNome());		
		lezioneDto.setDescrizione(lezione.getEvento().getDescrizione());
		lezioneDto.setIdEventoLezione(lezione.getEvento().getIdevento());
		lezioneDto.setLatitudine(lezione.getEvento().getAula().getLatitudine());
		lezioneDto.setLongitudine(lezione.getEvento().getAula().getLongitudine());
		lezioneDto.setInsegnamento(lezione.getEvento().getInsegnamento().getNome());
		lezioneDto.setDataFine(lezione.getEvento().getPrenotazione().getDataFine());
		lezioneDto.setDataInizio(lezione.getEvento().getPrenotazione().getDataInizio());
		lezioneDto.setIdInsegnamento(lezione.getEvento().getInsegnamento().getIdinsegnamento());
		lezioneDto.setIdPrenotazioneLezione(lezione.getEvento().getPrenotazione().getIdprenotazione());
		lezioneDto.setIdUserDocente(lezione.getEvento().getInsegnamento().getDocente().getUtente().getIdutente());
		lezioneDto.setDocente(lezione.getEvento().getInsegnamento().getDocente().getUtente().getNome() + ' ' +lezione.getEvento().getInsegnamento().getDocente().getUtente().getCognome());

		if (lezione.getEvento().getAula().getWifi() == 0)
			lezioneDto.setStatoWifi("Il wifi è debole o assente.");
		else
			lezioneDto.setStatoWifi("Il segnale wifi è disponibile.");
		return lezioneDto;	
	}

	@Override
	public <T> Object dtoToDomain(T dtoObject) {
		return dtoObject;
	}	
}
