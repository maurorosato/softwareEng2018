package it.unisalento.se.saw.restapi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.UtenteDto;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/utente")	
public class UtenteRestController {
	
	@Autowired
	IUtenteService utenteService;
	
	public UtenteRestController() {
		super();
	}
	
	public UtenteRestController(IUtenteService utenteService) {
		this.utenteService = utenteService;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Utente post(@RequestBody Utente utente) throws ParseException, UtenteNotFoundException {
		return utenteService.save(utente);
	}
	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UtenteDto> getAll() throws UtenteNotFoundException {
		List<Utente> utenti = utenteService.getAll();
		List<UtenteDto> utentiDto= new ArrayList<UtenteDto>();
		//utenti = (utenteService.getAll());
		Utente utente = new Utente();
		for(int i=0;i<utenti.size();i++) {
			UtenteDto utenteDto = new UtenteDto();
			utente= utenti.get(i);
			utenteDto.setNome(utente.getNome());
			utenteDto.setCognome(utente.getCognome());
			utentiDto.add(i, utenteDto);
		}
		return utentiDto;
	}	
	
	@GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	//quello che arriva in id sopra mettilo in id sotto
	public UtenteDto getById(@PathVariable("id") int id) throws UtenteNotFoundException{
		Utente utente=new Utente();
		//List<Studente> studente;
		//studente=studenteService.getAll();
		//int idutente=studente.get(id-1).getUtenteIdUtente();
		utente=utenteService.getById(id);
		UtenteDto utenteDto = new UtenteDto();
		utenteDto.setNome(utente.getNome());
		utenteDto.setCognome(utente.getCognome());
		return utenteDto;
	}
	
	/*
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Utente post(@RequestBody Utente utente) throws UtenteNotFoundException, ParseException {
		Utente user = new Utente();
		user.setNome("inserimento1");
		user.setCognome("inserimento2");
		user.setEmail("prova");
		user.setIdOrigin(0);
		user.setPassword("pass");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date date = (Date) formatter.parse("2012-02-02");
		user.setDataNascita(date);
		return utenteService.save(user);
	}
	*/
}
