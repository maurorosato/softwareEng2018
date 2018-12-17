package it.unisalento.se.saw.restapi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.ISegnalazioneService;
import it.unisalento.se.saw.converter.SegnalazioneConverter;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.dto.SegnalazioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;


@RestController()
@RequestMapping(value="/segnalazione")
public class SegnalazioneRestController {

	@Autowired
	ISegnalazioneService segnalazioneService;
	
	@Autowired
	IAulaService aulaService;
	
	@Autowired
	IDocenteService docenteService;
	
	public SegnalazioneRestController() {
		super();
	}
	
	public SegnalazioneRestController(ISegnalazioneService segnalazioneService) {
		this.segnalazioneService= segnalazioneService;
	}
	
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Segnalazione post(@RequestBody SegnalazioneDto segnalazioneDto) throws ParseException, AulaNotFoundException, DocenteNotFoundException{
		
		Segnalazione segnalazione;
		List<Aula> aule = aulaService.getAll();
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		segnalazione = SegnalazioneConverter.dtoToDomain(segnalazioneDto, aule, sqlDate);
		
		
		System.out.println("data: "+segnalazione.getData());
		System.out.println("descrizione "+segnalazione.getDescrizione());
		System.out.println("idAULA: "+segnalazione.getAula().getIdaula());
		System.out.println("statoSegnalazione: "+segnalazione.getStatoSegnalazione());
		System.out.println("oggettoInteressato:" +segnalazione.getOggettoInteressato());
		System.out.println("motivazione: "+segnalazione.getMotivazione());
		
		return segnalazioneService.save(segnalazione);

	}
	
	@RequestMapping (value="/getAll", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SegnalazioneDto> getAll() throws SegnalazioneNotFoundException, AulaNotFoundException, DocenteNotFoundException {
		
		List<SegnalazioneDto> segnalazioniDto= new ArrayList<SegnalazioneDto>();
		List<Segnalazione> segnalazioni = (segnalazioneService.getAll());
		List<Aula> aule = (aulaService.getAll());
		List<Docente> docenti = docenteService.getAll();
		Iterator<Segnalazione> segnalazioneIterator = segnalazioni.iterator();
				
		while(segnalazioneIterator.hasNext()){
			SegnalazioneDto segnalazioneDto = new SegnalazioneDto();
			Segnalazione segnalazione = segnalazioneIterator.next();
			
			segnalazioneDto = SegnalazioneConverter.domainToDto(segnalazione, aule, docenti);	
			segnalazioniDto.add(segnalazioneDto);
		}
	
		return segnalazioniDto;
	}
	
	@GetMapping (value="/getById/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public SegnalazioneDto getById(@PathVariable("id") int id) throws SegnalazioneNotFoundException, AulaNotFoundException, DocenteNotFoundException {
		
		List<Aula> aule = (aulaService.getAll());
		List<Docente> docenti = docenteService.getAll();
		Segnalazione segnalazione = segnalazioneService.getById(id);
		SegnalazioneDto segnalazioneDto = SegnalazioneConverter.domainToDto(segnalazione, aule, docenti);
		
		return segnalazioneDto;
	}
	
	@PatchMapping (value = "/cambiaStatoSegnalazione",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cambiaStatoSegnalazione(@RequestBody SegnalazioneDto segnalazioneDto, String stato) throws SegnalazioneNotFoundException {
   		segnalazioneService.cambiaStatoSegnalazione(segnalazioneDto);
    }
	
	@PatchMapping (value = "/updateDescrizioneSegnalazione",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateDescrizioneSegnalazione(@RequestBody SegnalazioneDto segnalazioneDto, String stato) throws SegnalazioneNotFoundException {
		System.out.println(segnalazioneDto.getDescrizione());
		System.out.println(segnalazioneDto.getIdSegnalazione());
		segnalazioneService.updateDescrizioneSegnalazione(segnalazioneDto);
}
    
}