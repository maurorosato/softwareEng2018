package it.unisalento.se.saw.converter;

import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.dto.AppelloEsameDto;
import it.unisalento.se.saw.dto.PrenotazioneDto;

public class AppelloEsameConverter implements IConverter{
	

	@Override
	public <T> Object domainToDto(T domainObject) {
		AppelloEsameDto appelloEsameDto = new AppelloEsameDto();
		AppelloEsame appelloEsame = (AppelloEsame) domainObject;

		appelloEsameDto.setTipologia(appelloEsame.getTipologia());
		appelloEsameDto.setIdAppelloEsame(appelloEsame.getIdappelloEsame());
		appelloEsameDto.setAula(appelloEsame.getEvento().getAula().getNome());
		appelloEsameDto.setDescrizione(appelloEsame.getEvento().getDescrizione());
		appelloEsameDto.setIdEventoAppelloEsame(appelloEsame.getEvento().getIdevento());	
		appelloEsameDto.setDataFine(appelloEsame.getEvento().getPrenotazione().getDataFine());
		appelloEsameDto.setInsegnamento(appelloEsame.getEvento().getInsegnamento().getNome());
		appelloEsameDto.setDataInizio(appelloEsame.getEvento().getPrenotazione().getDataInizio());
		appelloEsameDto.setIdInsegnamento(appelloEsame.getEvento().getInsegnamento().getIdinsegnamento());
		appelloEsameDto.setDocente(appelloEsame.getEvento().getPrenotazione().getDocente().getUtente().getNome() + ' ' +appelloEsame.getEvento().getPrenotazione().getDocente().getUtente().getCognome());
		
		return appelloEsameDto;
	}

	@Override
	public <T> Object dtoToDomain(T dtoObject) {
		PrenotazioneDto prenotazioneDto = (PrenotazioneDto) dtoObject;
		AppelloEsame appelloEsame = new AppelloEsame();
		appelloEsame.setTipologia(prenotazioneDto.getTipologiaEsame());
			
		return appelloEsame;
	}
}
