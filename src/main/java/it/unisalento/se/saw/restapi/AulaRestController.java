package it.unisalento.se.saw.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/aula")	
public class AulaRestController {

	@Autowired
	IAulaService aulaService;
	
	public AulaRestController() {
		super();
	}
	
	public AulaRestController(IAulaService aulaService) {
		this.aulaService = aulaService;
	}
	/*
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Corso post(@RequestBody Corso corso) throws ParseException, CorsoNotFoundException {
		return corsoService.save(corso);
	}
	*/
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Aula> getAll() throws AulaNotFoundException {
		return aulaService.getAll();
	}
}

