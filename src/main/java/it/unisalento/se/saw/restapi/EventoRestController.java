package it.unisalento.se.saw.restapi;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAppelloEsameService;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.converter.EventoConverter;
import it.unisalento.se.saw.converter.IConverter;
import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.EventoDto;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/evento")
public class EventoRestController {

	@Autowired
	IEventoService eventoService;
	
	@Autowired
	IAppelloEsameService appelloEsameService;
	
	@Autowired
	ICorsoDiStudioService corsoDiStudioService;
	
	@Autowired
	ILezioneService lezioneService;
	
	IConverter eventoConverter = (IConverter) new EventoConverter();

	public EventoRestController() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public EventoRestController(IEventoService eventoService, IAppelloEsameService appelloEsameService, ICorsoDiStudioService corsoDiStudioService, ILezioneService lezioneService ) {
		this.eventoService = eventoService;
		this.appelloEsameService= appelloEsameService;
		this.corsoDiStudioService = corsoDiStudioService;
		this.lezioneService = lezioneService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EventoDto> getAll() throws EventoNotFoundException, AulaNotFoundException, LezioneNotFoundException, DocenteNotFoundException, CorsoDiStudioNotFoundException, InsegnamentoNotFoundException, UtenteNotFoundException, AppelloEsameNotFoundException, PrenotazioneNotFoundException {
		
		List<EventoDto> eventiDto= new ArrayList<EventoDto>();
		
		List<Evento> eventi = eventoService.getAll();
		List<AppelloEsame> appelli = appelloEsameService.getAll();
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());
		
		Iterator<Evento> eventoIterator = eventi.iterator();

		while (eventoIterator.hasNext()){
			Evento evento = eventoIterator.next();
			EventoDto eventoDto = new EventoDto();
			if (evento.getIdevento() != 1){
				
				Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
				Iterator<AppelloEsame> appelloIterator = appelli.iterator();

				eventoDto = (EventoDto) eventoConverter.domainToDto(evento);
				eventoDto.setImage("lessonsIcon.png");
				eventoDto.setLezioneOrEsame("lezione");
				
				while (corsoIterator.hasNext()){
					CorsoDiStudio corso = corsoIterator.next();
					if (corso.getIdcorsoDiStudio() == evento.getInsegnamento().getCorsoDiStudioIdcorsoDiStudio())
						eventoDto.setCorso(corso.getNomeCorso());
				}
				
				while (appelloIterator.hasNext()){
					AppelloEsame appello = appelloIterator.next();
					if (appello.getEvento().getIdevento() == evento.getIdevento()){
						eventoDto.setDescrizione("TIPOLOGIA: "+appello.getTipologia() + " DESCRIZIONE: "+ evento.getDescrizione());
						eventoDto.setLezioneOrEsame("esame");
						eventoDto.setImage("examsIcon.png");
					}
				}
				eventiDto.add(eventoDto);
			}			
		}
	return eventiDto;
	}
	
	@PatchMapping (value = "/modificaEventoAulaDisponibile",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void modificaEventoAulaDisponibile(@RequestBody EventoDto eventoDto) throws AulaNotFoundException, LezioneNotFoundException, AppelloEsameNotFoundException, EventoNotFoundException {
		switch(eventoDto.getLezioneOrEsame()){
			case "lezione" :
				Lezione lezione = lezioneService.getById(eventoDto.getIdEvento());
				eventoService.modificaEventoAuladisponibile(lezione.getEvento().getIdevento(),eventoDto.getIdAula());
				break;
			case "esame" :
				AppelloEsame appelloEsame = appelloEsameService.getById(eventoDto.getIdEvento());
				eventoService.modificaEventoAuladisponibile(appelloEsame.getEvento().getIdevento(),eventoDto.getIdAula());
				break;
		}
	}
}
