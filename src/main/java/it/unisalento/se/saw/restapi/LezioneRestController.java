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
import it.unisalento.se.saw.converter.LezioneConverter;
import it.unisalento.se.saw.converter.PrenotazioneConverter;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
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
	
	public LezioneRestController() {
		super();
	}
	
	public LezioneRestController(ILezioneService lezioneService) {
		this.lezioneService = lezioneService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getAll() throws LezioneNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();
		List<Aula> aule = aulaService.getAll();
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		List<Lezione> lezioni = lezioneService.getAll();
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();
		List<Evento> eventi = eventoService.getAll();
		List<Docente> docenti = docenteService.getAll();
		
		Iterator<Lezione> lezioneIterator = lezioni.iterator();
		while(lezioneIterator.hasNext()){
			Lezione lezione = lezioneIterator.next();
			LezioneDto lezioneDto;
			lezioneDto = LezioneConverter.domainToDto(lezione,prenotazioni,eventi,insegnamenti,aule,docenti);

			lezioniDto.add(lezioneDto);
		}
		return lezioniDto;
	}

	@RequestMapping(value="/getLezioniInsegnamento/{idInsegnamento}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getLezioniInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws LezioneNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();
		
		List<Aula> aule = aulaService.getAll();
		List<Evento> eventi = eventoService.getAll();
		List<Docente> docenti = docenteService.getAll();
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();
		
		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setIdinsegnamento(idInsegnamento);
		List<Lezione> lezioniInsegnamento = lezioneService.getLezioniInsegnamento(insegnamento);
		
		Iterator<Lezione> lezioneIterator = lezioniInsegnamento.iterator();
		while(lezioneIterator.hasNext()){
			Lezione lezione = lezioneIterator.next();
			LezioneDto lezioneDto;
			lezioneDto = LezioneConverter.domainToDto(lezione,prenotazioni,eventi,insegnamenti,aule,docenti);

			lezioniDto.add(lezioneDto);
		}
		
		return lezioniDto;

	}
	@RequestMapping(value="/getAllStudente/{idCorsoStudente}/{annoCorso}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getAllStudente(@PathVariable("idCorsoStudente") int idCorsoStudente,@PathVariable("annoCorso") String annoCorso) throws LezioneNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();
		
		List<Aula> aule = aulaService.getAll();
		List<Evento> eventi = eventoService.getAll();
		List<Docente> docenti = docenteService.getAll();
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();

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
			LezioneDto lezioneDto = LezioneConverter.domainToDto(lezione,prenotazioni,eventi,insegnamenti,aule,docenti);

			lezioniDto.add(lezioneDto);
		}
		return lezioniDto;
	}

	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Lezione post(@RequestBody PrenotazioneDto prenotazioneDto) throws InsegnamentoNotFoundException, AppelloEsameNotFoundException{
		Evento evento = new Evento();
		Lezione lezione = new Lezione();
		Prenotazione prenotazione = new Prenotazione();
		
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		
		prenotazione = PrenotazioneConverter.dtoToDomain(prenotazioneDto);
		prenotazioneService.save(prenotazione);
		
		evento = EventoConverter.dtoToDomain(prenotazioneDto, insegnamenti);
		evento.setDescrizione("Descrizione Lezione non inserita");
		evento.setPrenotazione(prenotazione);
		eventoService.save(evento);
		
		lezione.setGradimento(0);
		lezione.setEvento(evento);
		
		return lezioneService.save(lezione);
	}
}