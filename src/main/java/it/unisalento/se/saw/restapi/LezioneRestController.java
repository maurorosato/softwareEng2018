package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.converter.EventoConverter;
import it.unisalento.se.saw.converter.IConverter;
import it.unisalento.se.saw.converter.LezioneConverter;
import it.unisalento.se.saw.converter.PrenotazioneConverter;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.LezioneDto;
import it.unisalento.se.saw.dto.PrenotazioneDto;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;

@RestController()
@RequestMapping(value="/lezione")
public class LezioneRestController {

	@Autowired
	ILezioneService lezioneService;
	
	@Autowired
	IAulaService aulaService;
	
	@Autowired
	IEventoService eventoService;
	
	@Autowired
	IInsegnamentoService insegnamentoService;
	
	@Autowired
	IPrenotazioneService prenotazioneService;
	
	@Autowired
	IDocenteService docenteService;
	
	IConverter lezioneConverter = new LezioneConverter();
	IConverter prenotazioneConverter = new PrenotazioneConverter();

	
	public LezioneRestController() {
		super();
	}
	
	public LezioneRestController(ILezioneService lezioneService, IAulaService aulaService, IEventoService eventoService, IInsegnamentoService insegnamentoService, IPrenotazioneService prenotazioneService, IDocenteService docenteService) {
		this.lezioneService = lezioneService;
		this.aulaService = aulaService;
		this.eventoService = eventoService;
		this.insegnamentoService = insegnamentoService;
		this.lezioneService = lezioneService;
		this.prenotazioneService = prenotazioneService;
	}
	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getAll() throws LezioneNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();
		List<Lezione> lezioni = lezioneService.getAll();
		
		Iterator<Lezione> lezioneIterator = lezioni.iterator();
		while(lezioneIterator.hasNext()){
			Lezione lezione = lezioneIterator.next();
			LezioneDto lezioneDto = new LezioneDto();
			if (lezione.getIdlezione() != 1){
				lezioneDto = (LezioneDto) lezioneConverter.domainToDto(lezione);
				lezioniDto.add(lezioneDto);
			}
		}
		return lezioniDto;
	}

	@RequestMapping(value="/getLezioniInsegnamento/{idInsegnamento}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getLezioniInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws LezioneNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();
		
		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setIdinsegnamento(idInsegnamento);
		List<Lezione> lezioniInsegnamento = lezioneService.getLezioniInsegnamento(insegnamento);
		
		Iterator<Lezione> lezioneIterator = lezioniInsegnamento.iterator();
		while(lezioneIterator.hasNext()){
			Lezione lezione = lezioneIterator.next();
			LezioneDto lezioneDto;
			lezioneDto = (LezioneDto) lezioneConverter.domainToDto(lezione);

			lezioniDto.add(lezioneDto);
		}
		return lezioniDto;
	}
	
	@RequestMapping(value="/getAllStudente/{idCorsoStudente}/{annoCorso}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getAllStudente(@PathVariable("idCorsoStudente") int idCorsoStudente,@PathVariable("annoCorso") String annoCorso) throws LezioneNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();

		int anno = 0;
		
		switch (annoCorso){
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
		
		List<Lezione> lezioni = lezioneService.getAllStudente(idCorsoStudente,anno);
		Iterator<Lezione> lezioneIterator = lezioni.iterator();

		while(lezioneIterator.hasNext()){
			Lezione lezione = lezioneIterator.next();
			LezioneDto lezioneDto = (LezioneDto) lezioneConverter.domainToDto(lezione);
			lezioniDto.add(lezioneDto);
		}
		return lezioniDto;
	}

	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Lezione post(@RequestBody PrenotazioneDto prenotazioneDto) throws InsegnamentoNotFoundException, AppelloEsameNotFoundException{
		Evento evento = new Evento();
		Lezione lezione = new Lezione();
		Prenotazione prenotazione = new Prenotazione();
		
		int hour = prenotazioneDto.getDataFine().getHours()-1;	
		prenotazioneDto.getDataFine().setHours(hour);
		
		int hour2 = prenotazioneDto.getDataInizio().getHours()-1;	
		prenotazioneDto.getDataInizio().setHours(hour2);
		
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		
		prenotazione = (Prenotazione) prenotazioneConverter.dtoToDomain(prenotazioneDto);
		prenotazioneService.save(prenotazione);
		
		evento = EventoConverter.dtoToDomain(prenotazioneDto, insegnamenti);
		evento.setDescrizione("Descrizione Lezione non inserita");
		evento.setPrenotazione(prenotazione);
		eventoService.save(evento);
		
		lezione.setEvento(evento);
		
		return lezioneService.save(lezione);
	}
}