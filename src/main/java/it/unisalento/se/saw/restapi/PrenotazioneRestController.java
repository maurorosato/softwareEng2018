package it.unisalento.se.saw.restapi;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAppelloEsameService;
import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.converter.AppelloEsameConverter;
import it.unisalento.se.saw.converter.EventoConverter;
import it.unisalento.se.saw.converter.PrenotazioneConverter;
import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.PrenotazioneDto;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
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
	
	@Autowired
	IAppelloEsameService appelloEsameService;
	
	@Autowired
	IEventoService eventoService;
	
	@Autowired
	IInsegnamentoService insegnamentoService;
	
	public PrenotazioneRestController() {
		super(); 
	}
	
	public PrenotazioneRestController(IPrenotazioneService prenotazioneService) {
		this.prenotazioneService = prenotazioneService;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Prenotazione post(@RequestBody PrenotazioneDto prenotazioneDto) throws PrenotazioneNotFoundException, ParseException, AppelloEsameNotFoundException, InsegnamentoNotFoundException {
		Prenotazione prenotazioneSave = new Prenotazione();	
		prenotazioneSave = PrenotazioneConverter.dtoToDomain(prenotazioneDto);
		
		return prenotazioneService.save(prenotazioneSave);	
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PrenotazioneDto> getAll() throws PrenotazioneNotFoundException, DocenteNotFoundException {
		
		List<PrenotazioneDto> prenotazioniDto = new ArrayList<PrenotazioneDto>();
		List<Docente> docenti = docenteService.getAll();
		List<Prenotazione> prenotazioni = (prenotazioneService.getAll());
		Iterator<Prenotazione> prenotazioneIterator = prenotazioni.iterator();
		
		while(prenotazioneIterator.hasNext()){
			Prenotazione prenotazione = prenotazioneIterator.next();
			//PrenotazioneDto prenotazioneDto = null;
			//System.out.println(prenotazione.getIdprenotazione());
			//if(prenotazione.getIdprenotazione()!=1){
			//	System.out.println("sono dentro");
			PrenotazioneDto prenotazioneDto = PrenotazioneConverter.domainToDto(prenotazione/*,docenti*/);
			//}
			prenotazioniDto.add(prenotazioneDto);
		}
		return prenotazioniDto;
	}
}
