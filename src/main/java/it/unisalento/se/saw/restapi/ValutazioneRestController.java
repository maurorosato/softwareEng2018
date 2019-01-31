package it.unisalento.se.saw.restapi;


import org.springframework.http.MediaType;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Valutazione;
import it.unisalento.se.saw.dto.IscrizioneDto;
import it.unisalento.se.saw.dto.MaterialeDidatticoDto;
import it.unisalento.se.saw.dto.ValutazioneDto;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IValutazioneService;
import it.unisalento.se.saw.converter.IConverter;
import it.unisalento.se.saw.converter.MaterialeDidatticoConverter;
import it.unisalento.se.saw.converter.ValutazioneConverter;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.IscrizioneNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.exceptions.MaterialeDidatticoNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.exceptions.ValutazioneNotFoundException;

@RestController()
@RequestMapping(value="/valutazione")
public class ValutazioneRestController {
	
	@Autowired
	IValutazioneService valutazioneService;
	
	@Autowired
	IStudenteService studenteService;
	
	IConverter valutazioneConverter = new ValutazioneConverter();
	
	public ValutazioneRestController(IValutazioneService valutazioneService, IStudenteService studenteService) {
		this.valutazioneService = valutazioneService;
		this.studenteService = studenteService;
	}
	
	public ValutazioneRestController() {
		super();
	}
	
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody ValutazioneDto valutazioneDto) throws ValutazioneNotFoundException, StudenteNotFoundException {
		List<Studente> studenti = studenteService.getAll();
		Valutazione valutazione = new Valutazione();
		Studente studente = new Studente();
		valutazione = (Valutazione) valutazioneConverter.dtoToDomain(valutazioneDto);
//		valutazione = ValutazioneConverter.DtoToDomain(valutazioneDto,studenti);
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			Studente student = studenteIterator.next();
			if(student.getUtente().getIdutente() == valutazioneDto.getIdStudente()){
				studente.setIdstudente(student.getIdstudente());
			}
		}
		studente.setIdstudente(valutazioneDto.getIdStudente());
		valutazione.setStudente(studente);
		valutazioneService.save(valutazione);
	}
	
	@RequestMapping(value="/getValutazioneStudente/{id}/{idUserStudente}/{oggettoValutato}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ValutazioneDto> getValutazione(@PathVariable("id") int id,@PathVariable("idUserStudente") int idUserStudente, @PathVariable("oggettoValutato") String oggettoValutato) throws ValutazioneNotFoundException, StudenteNotFoundException {
		List<ValutazioneDto> valutazioneListaDto = new ArrayList<ValutazioneDto>();
		List<Studente> studenti = studenteService.getAll();
		Studente studente = new Studente();
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			Studente stud = studenteIterator.next();
			if(stud.getUtente().getIdutente() == idUserStudente){
				studente.setIdstudente(stud.getIdstudente());
			}
		}
		
		switch(oggettoValutato){
			case "lezione":
				Lezione lezione = new Lezione();
				lezione.setIdlezione(id);
				List<Valutazione> valutazioneListaLezione = valutazioneService.getValutazioneStudenteLezione(studente,lezione);
				Iterator<Valutazione> valutazioneLezioneIterator = valutazioneListaLezione.iterator();
				
				while(valutazioneLezioneIterator.hasNext()){
					Valutazione valutazioneL = valutazioneLezioneIterator.next();
					ValutazioneDto valutazioneLDto = new ValutazioneDto();
					if (valutazioneL.getNota() != "default"){
						valutazioneLDto = (ValutazioneDto) valutazioneConverter.domainToDto(valutazioneL);
						//valutazioneLDto = ValutazioneConverter.domainToDto(valutazioneL);
					}
					valutazioneListaDto.add(valutazioneLDto);
					System.out.println("id lez: "+valutazioneListaDto.get(0).getIdLezione());

				}
				break;
			case "matDidattico":
				List<Valutazione> valutazioneListaMatDidattico = valutazioneService.getValutazioneStudenteMatDidattico(studente,id);
				Iterator<Valutazione> valutazioneMatDidatticoIterator = valutazioneListaMatDidattico.iterator();
				
				while(valutazioneMatDidatticoIterator.hasNext()){
					Valutazione valutazioneM = valutazioneMatDidatticoIterator.next();
					ValutazioneDto valutazioneMDto = (ValutazioneDto) valutazioneConverter.domainToDto(valutazioneM);
					//ValutazioneDto valutazioneMDto = ValutazioneConverter.domainToDto(valutazioneM);
					
					valutazioneListaDto.add(valutazioneMDto);
				}
				break;
		}
		System.out.println(valutazioneListaDto);
		return valutazioneListaDto;
	}
	
	@RequestMapping(value="/getValutazione/{id}/{oggettoValutato}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ValutazioneDto> getValutazioneLezione(@PathVariable("id") int id,@PathVariable("oggettoValutato") String oggettoValutato) throws ValutazioneNotFoundException{
		List<ValutazioneDto> valutazioneListaDto = new ArrayList<ValutazioneDto>();
		
		switch(oggettoValutato){
		case "lezione":
			Lezione lezione = new Lezione();
			lezione.setIdlezione(id);
			List<Valutazione> valutazioneListaLezione = valutazioneService.getValutazioneLezione(lezione);
			Iterator<Valutazione> valutazioneLezioneIterator = valutazioneListaLezione.iterator();
			
			while(valutazioneLezioneIterator.hasNext()){
				Valutazione valutazioneL = valutazioneLezioneIterator.next();
				
				ValutazioneDto valutazioneLDto = (ValutazioneDto) valutazioneConverter.domainToDto(valutazioneL);
				//ValutazioneDto valutazioneLDto = ValutazioneConverter.domainToDto(valutazioneL);
				
				valutazioneListaDto.add(valutazioneLDto);
			}
			break;
		case "matDidattico":
			List<Valutazione> valutazioneListaMatDidattico = valutazioneService.getValutazioneMatDidattico(id);
			Iterator<Valutazione> valutazioneMatDidatticoIterator = valutazioneListaMatDidattico.iterator();
			
			while(valutazioneMatDidatticoIterator.hasNext()){
				Valutazione valutazioneM = valutazioneMatDidatticoIterator.next();
				ValutazioneDto valutazioneMDto = (ValutazioneDto) valutazioneConverter.domainToDto(valutazioneM);

				//ValutazioneDto valutazioneMDto = ValutazioneConverter.domainToDto(valutazioneM);
				
				valutazioneListaDto.add(valutazioneMDto);
			}
			break;
		}
		
		return valutazioneListaDto;
	}
}
