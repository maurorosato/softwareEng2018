package it.unisalento.se.saw.restapi;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAppelloEsame;
import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.EventoDto;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/evento")
public class EventoRestController {

	@Autowired
	IEventoService eventoService;
	
	@Autowired
	IAulaService aulaService;
	
	@Autowired
	ICorsoDiStudioService corsoDiStudioService;
	
	@Autowired
	ILezioneService lezioneService;
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	IInsegnamentoService insegnamentoService;
	
	@Autowired
	IUtenteService utenteService;
	
	@Autowired
	IAppelloEsame appelloEsameService;
	
	public EventoRestController() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public EventoRestController(IEventoService eventoService) {
		this.eventoService = eventoService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EventoDto> getAll() throws EventoNotFoundException, AulaNotFoundException, LezioneNotFoundException, DocenteNotFoundException, CorsoDiStudioNotFoundException, InsegnamentoNotFoundException, UtenteNotFoundException, AppelloEsameNotFoundException {
		
		List<EventoDto> eventiDto= new ArrayList<EventoDto>();
		
		List<Evento> eventi = eventoService.getAll();
		Iterator<Evento> eventoIterator = eventi.iterator();

		List<Aula> aule = aulaService.getAll();
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());
		List<Insegnamento> insegnamenti = (insegnamentoService.getAll());
		List<Docente> docenti = (docenteService.getAll());
		List<Lezione> lezioni = lezioneService.getAll();
		List<AppelloEsame> appelli = appelloEsameService.getAll();
		List<Utente> utenti = utenteService.getAll();
		
		float numeroFloat = 0;
		int idCorso = 0,idDocente = 0, idUtente = 0;
		
		while (eventoIterator.hasNext()){
			Evento evento = eventoIterator.next();
			EventoDto eventoDto = new EventoDto();
			
			eventoDto.setIdEvento(evento.getIdevento());
			eventoDto.setDescrizione(evento.getDescrizione());
			eventoDto.setGradimento(numeroFloat);
			
			Iterator<Aula> aulaIterator = aule.iterator();
			while (aulaIterator.hasNext()){
				Aula aula = aulaIterator.next();
				if (aula.getIdaula() == evento.getAula().getIdaula())
					eventoDto.setAula(aula.getNome());
			}
			Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
			while (insegnamentoIterator.hasNext()){
				Insegnamento insegnamento = insegnamentoIterator.next();
				if (insegnamento.getIdinsegnamento() == evento.getInsegnamento().getIdinsegnamento()){
					eventoDto.setInsegnamento(insegnamento.getNome());
					idCorso = insegnamento.getCorsoDiStudioIdcorsoDiStudio();
					idDocente = insegnamento.getDocente().getIddocente();
				}
			}
			Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
			while (corsoIterator.hasNext()){
				CorsoDiStudio corso = corsoIterator.next();
				if (corso.getIdcorsoDiStudio() == idCorso)
					eventoDto.setCorso(corso.getNomeCorso());
			}
			Iterator<Lezione> lezioneIterator = lezioni.iterator();
			while (lezioneIterator.hasNext()){
				Lezione lezione = lezioneIterator.next();
				if (lezione.getEvento().getIdevento() == evento.getIdevento())
					eventoDto.setGradimento(lezione.getGradimento());
			}
			
			Iterator<AppelloEsame> appelloIterator = appelli.iterator();
			while (appelloIterator.hasNext()){
				AppelloEsame appello = appelloIterator.next();
				if (appello.getEvento().getIdevento() == evento.getIdevento())
					eventoDto.setDescrizione("TIPOLOGIA: "+appello.getTipologia() + " DESCRIZIONE: "+ evento.getDescrizione());
			}
			
			Iterator<Docente> docenteIterator = docenti.iterator();
			while (docenteIterator.hasNext()){
				Docente docente = docenteIterator.next();
				if (docente.getIddocente() == idDocente)
					idUtente = docente.getUtente().getIdutente();	
			}
			Iterator<Utente> utenteIterator = utenti.iterator();
			while (utenteIterator.hasNext()){
				Utente utente = utenteIterator.next();
				if (utente.getIdutente() == idUtente)
					eventoDto.setDocente(utente.getNome() + " " + utente.getCognome());
			}
			
			if(eventoDto.getGradimento() == 0)
				eventoDto.setImage("examsIcon.png");
			else
				eventoDto.setImage("lessonsIcon.png");
			
		 eventiDto.add(eventoDto);
		}
	return eventiDto;
	}
}
		/*
		for (int i=0; i < eventi.size(); i++){
			EventoDto eventoDto = new EventoDto();
			eventoDto.setIdEvento(eventi.get(i).getIdevento());
			eventoDto.setDescrizione(eventi.get(i).getDescrizione());
			eventoDto.setGradimento(numeroFloat);
			eventoDto.setData(eventi.get(i).getData());

			for (int j=0; j < aule.size(); j++){
				if( aule.get(j).getIdaula() == eventi.get(i).getAula().getIdaula()){
					eventoDto.setAula(aule.get(j).getNome());
				}
			}
			for (int t=0; t < insegnamenti.size(); t++){
				if( insegnamenti.get(t).getIdinsegnamento() == eventi.get(i).getInsegnamento().getIdinsegnamento()){
					eventoDto.setInsegnamento(insegnamenti.get(t).getNome());
					idCorso = insegnamenti.get(t).getCorsoDiStudioIdcorsoDiStudio();
					idDocente= insegnamenti.get(t).getDocente().getIddocente();
				}
			}
			for (int z=0; z < corsi.size(); z++){
				if(corsi.get(z).getIdcorsoDiStudio() == idCorso){
					eventoDto.setCorso(corsi.get(z).getNomeCorso());
				}
			}
			for (int y=0; y < lezioni.size(); y++){
				if (lezioni.get(y).getEvento().getIdevento() == eventoDto.getIdEvento()){
					eventoDto.setGradimento(lezioni.get(y).getGradimento());
				}
			}
			for (int r=0; r < docenti.size(); r++){
				if (docenti.get(r).getIddocente() == idDocente){
					idUtente = docenti.get(r).getUtente().getIdutente();
				}
			}
			for (int s=0; s < utenti.size(); s++){
				if(utenti.get(s).getIdutente() == idUtente){
					eventoDto.setDocente(utenti.get(s).getNome()+ " "+ utenti.get(s).getCognome());
				}
			}
		
			if(eventoDto.getGradimento() == 0)
				eventoDto.setImage("examsIcon.png");
			else
				eventoDto.setImage("lessonsIcon.png");
			eventiDto.add(i, eventoDto);
		}
		*/

