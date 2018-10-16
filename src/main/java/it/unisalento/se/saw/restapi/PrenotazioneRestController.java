package it.unisalento.se.saw.restapi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.converter.PrenotazioneConverter;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.PrenotazioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;

@RestController()
@RequestMapping(value="/prenotazione")
public class PrenotazioneRestController {
	
	@Autowired
	IPrenotazioneService prenotazioneService;
	
	@Autowired
	IAulaService aulaService;
	
	@Autowired
	IDocenteService docenteService;
	
	public PrenotazioneRestController() {
		super(); 
	}
	
	public PrenotazioneRestController(IPrenotazioneService prenotazioneService) {
		this.prenotazioneService = prenotazioneService;
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PrenotazioneDto> getAll() throws PrenotazioneNotFoundException, DocenteNotFoundException {
		
		List<PrenotazioneDto> prenotazioniDto = new ArrayList<PrenotazioneDto>();
		List<Docente> docenti = docenteService.getAll();
		List<Prenotazione> prenotazioni = (prenotazioneService.getAll());
		Iterator<Prenotazione> prenotazioneIterator = prenotazioni.iterator();
		
		while(prenotazioneIterator.hasNext()){
			Prenotazione prenotazione = prenotazioneIterator.next();
			PrenotazioneDto prenotazioneDto = new PrenotazioneDto();
			prenotazioneDto = PrenotazioneConverter.domainToDto(prenotazione,docenti);

			prenotazioniDto.add(prenotazioneDto);
		}
		return prenotazioniDto;
	}
}
