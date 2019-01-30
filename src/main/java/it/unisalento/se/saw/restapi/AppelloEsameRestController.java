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
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.dto.AppelloEsameDto;
import it.unisalento.se.saw.dto.PrenotazioneDto;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;

@RestController()
@RequestMapping(value="/appelloEsame")
public class AppelloEsameRestController {
	
	@Autowired
	IAulaService aulaService;
	
	@Autowired
	IAppelloEsameService appelloEsameService;
	
	@Autowired
	IEventoService eventoService;
	
	@Autowired
	IInsegnamentoService insegnamentoService;
	
	@Autowired
	IPrenotazioneService prenotazioneService;
	
	@Autowired
	IDocenteService docenteService;
	
	public AppelloEsameRestController() {
		super(); 
	}
	
	public AppelloEsameRestController(IAppelloEsameService appelloEsameService, IAulaService aulaService, IEventoService eventoService, IInsegnamentoService insegnamentoService, IPrenotazioneService prenotazioneService, IDocenteService docenteService) {
		this.appelloEsameService = appelloEsameService;
		this.aulaService = aulaService;
		this.eventoService = eventoService;
		this.insegnamentoService = insegnamentoService;
		this.prenotazioneService = prenotazioneService;
		this.docenteService = docenteService;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public AppelloEsame post(@RequestBody PrenotazioneDto prenotazioneDto) throws InsegnamentoNotFoundException, AppelloEsameNotFoundException{
		AppelloEsame appelloEsame = new AppelloEsame();
		Prenotazione prenotazione = new Prenotazione();
		Evento evento = new Evento();
		
		int hour = prenotazioneDto.getDataFine().getHours()-1;	
		prenotazioneDto.getDataFine().setHours(hour);
		
		int hour2 = prenotazioneDto.getDataInizio().getHours()-1;	
		prenotazioneDto.getDataInizio().setHours(hour2);

		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		
		prenotazione = PrenotazioneConverter.dtoToDomain(prenotazioneDto);
		prenotazioneService.save(prenotazione);
		
		evento = EventoConverter.dtoToDomain(prenotazioneDto, insegnamenti);
		evento.setDescrizione(prenotazioneDto.getDescrizioneEsame());
		evento.setPrenotazione(prenotazione);
		eventoService.save(evento);
		
		appelloEsame = AppelloEsameConverter.dtoToDomain(prenotazioneDto);
		appelloEsame.setEvento(evento);
		
		return appelloEsameService.save(appelloEsame);
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AppelloEsameDto> getAll() throws AppelloEsameNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, DocenteNotFoundException{
		List<AppelloEsameDto> appelliEsameDto= new ArrayList<AppelloEsameDto>();
		List<Aula> aule = aulaService.getAll();
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		List<AppelloEsame> appelli = appelloEsameService.getAll();
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();
		List<Evento> eventi = eventoService.getAll();
		List<Docente> docenti = docenteService.getAll();
		
		Iterator<AppelloEsame> appelloEsameIterator = appelli.iterator();
		while(appelloEsameIterator.hasNext()){
			AppelloEsame appelloEsame = appelloEsameIterator.next();
			AppelloEsameDto appelloEsameDto = new AppelloEsameDto();
			
			if(appelloEsame.getIdappelloEsame() != 1){
				appelloEsameDto = AppelloEsameConverter.domainToDto(appelloEsame/*,prenotazioni,eventi,insegnamenti,aule,docenti*/);
				appelliEsameDto.add(appelloEsameDto);
			}
		}

		return appelliEsameDto;		
	}
	
	@RequestMapping(value="/getAllStudente/{idCorsoStudente}/{annoCorso}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AppelloEsameDto> getAllStudente(@PathVariable("idCorsoStudente") int idCorsoStudente,@PathVariable("annoCorso") String annoCorso) throws AppelloEsameNotFoundException, PrenotazioneNotFoundException, EventoNotFoundException, AulaNotFoundException, InsegnamentoNotFoundException, DocenteNotFoundException{
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
		
		List<Aula> aule = aulaService.getAll();
		List<Evento> eventi = eventoService.getAll();
		List<Docente> docenti = docenteService.getAll();
		List<AppelloEsame> appelli = appelloEsameService.getAllStudente(idCorsoStudente,anno);
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		List<AppelloEsameDto> appelliEsameDto= new ArrayList<AppelloEsameDto>();
		Iterator<AppelloEsame> appelloEsameIterator = appelli.iterator();

		while(appelloEsameIterator.hasNext()){
			AppelloEsame appelloEsame = appelloEsameIterator.next();
			
			AppelloEsameDto appelloEsameDto = new AppelloEsameDto();
			appelloEsameDto = AppelloEsameConverter.domainToDto(appelloEsame /*,prenotazioni,eventi,insegnamenti,aule,docenti*/);

			appelliEsameDto.add(appelloEsameDto);
		}

		return appelliEsameDto;		
	}
	@RequestMapping(value="/getAppelliEsameInsegnamento/{idInsegnamento}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AppelloEsameDto> getAppelliEsameInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws AulaNotFoundException, EventoNotFoundException, DocenteNotFoundException, InsegnamentoNotFoundException, PrenotazioneNotFoundException, AppelloEsameNotFoundException {
		List<AppelloEsameDto> appelliEsameDto= new ArrayList<AppelloEsameDto>();
		
		List<Aula> aule = aulaService.getAll();
		List<Evento> eventi = eventoService.getAll();
		List<Docente> docenti = docenteService.getAll();
		List<Insegnamento> insegnamenti = insegnamentoService.getAll();
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();
		
		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setIdinsegnamento(idInsegnamento);
		List<AppelloEsame> appelliInsegnamento = appelloEsameService.getAppelliEsameInsegnamento(insegnamento);
		
		Iterator<AppelloEsame> appelloIterator = appelliInsegnamento.iterator();
		while(appelloIterator.hasNext()){
			AppelloEsame appelloEsame = appelloIterator.next();
			AppelloEsameDto appelloEsameDto;
			appelloEsameDto = AppelloEsameConverter.domainToDto(appelloEsame/*,prenotazioni,eventi,insegnamenti,aule,docenti*/);

			appelliEsameDto.add(appelloEsameDto);
		}
		return appelliEsameDto;
	}
}
