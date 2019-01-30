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
import it.unisalento.se.saw.Iservices.IIscrizioneService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.converter.IscrizioneConverter;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.IscrizioneDto;
import it.unisalento.se.saw.exceptions.IscrizioneNotFoundException;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;

@RestController()
@RequestMapping(value="/iscrizione")
public class IscrizioneRestController {
	@Autowired
	IIscrizioneService iscrizioneService;
	@Autowired
	IStudenteService studenteService;
	
	public IscrizioneRestController() {
		super(); 
	}
	
	public IscrizioneRestController(IIscrizioneService iscrizioneService, IStudenteService studenteService) {
		this.iscrizioneService = iscrizioneService;
		this.studenteService = studenteService;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody IscrizioneDto iscrizioneDto) throws IscrizioneNotFoundException, StudenteNotFoundException {
		
		List<Studente> studenti = studenteService.getAll();
		Iscrizione iscrizione = new Iscrizione();
		iscrizione = IscrizioneConverter.DtoToDomain(iscrizioneDto,studenti);
		
		iscrizioneService.save(iscrizione);	
	}
	
	@RequestMapping(value="/getIscrizione/{idInsegnamento}/{idUserStudente}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<IscrizioneDto> getIscrizione(@PathVariable("idInsegnamento") int idInsegnamento,@PathVariable("idUserStudente") int idUserStudente) throws IscrizioneNotFoundException, StudenteNotFoundException  {

		List<IscrizioneDto> iscrizioniDto= new ArrayList<IscrizioneDto>();
		Studente student = new Studente();
		Insegnamento insegnamento = new Insegnamento();
		
		List<Studente> studenti = studenteService.getAll();
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			Studente studente = studenteIterator.next();
			if(studente.getUtente().getIdutente() == idUserStudente){
				student.setIdstudente(studente.getIdstudente());
			}
		}
		insegnamento.setIdinsegnamento(idInsegnamento);
		
		List<Iscrizione> iscrizioni = iscrizioneService.getIscrizione(insegnamento,student);
		Iterator<Iscrizione> iscrizioneIterator = iscrizioni.iterator();
		while(iscrizioneIterator.hasNext()){
			Iscrizione iscr = iscrizioneIterator.next();
			IscrizioneDto iscrDto = IscrizioneConverter.DomainToDto(iscr/*, studenti*/);
					
			iscrizioniDto.add(iscrDto);
		}
	
		return iscrizioniDto;
	}
	
	@RequestMapping (value = "/rimuoviIscrizione/{idIscrizione}", method = RequestMethod.GET)
	public void rimuoviIscrizione(@PathVariable("idIscrizione") int idIscrizione) throws IscrizioneNotFoundException {
		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdiscrizione(idIscrizione);
		System.out.println(idIscrizione);
		
		iscrizioneService.rimuoviIscrizione(iscrizione);
	}
}
