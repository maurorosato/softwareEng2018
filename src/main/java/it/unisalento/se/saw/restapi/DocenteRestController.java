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

import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/docente")	
public class DocenteRestController {

	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	IUtenteService utenteService;
	
	@Autowired
	INumeroTelefonoService numeroService;
	
	public DocenteRestController() {
		super();
	}
	
	public DocenteRestController(IDocenteService docenteService) {
		this.docenteService= docenteService;
	}
	/*
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Docente post(@RequestBody Docente docente) throws ParseException, DocenteNotFoundException {
		return docenteService.save(docente);
	}
	*/
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DocenteDto> getAll() throws DocenteNotFoundException, UtenteNotFoundException {
		List<DocenteDto> docentiDto= new ArrayList<DocenteDto>();
		List<Docente> docenti = null;
		docenti = (docenteService.getAll());
		int idUtente;
		for(int i=0;i<docenti.size();i++) {
			DocenteDto docenteDto = new DocenteDto();
			idUtente = docenti.get(i).getUtente().getIdutente();
			Utente utente = new Utente();
			utente = utenteService.getById(idUtente);		
			docenteDto.setNome(utente.getNome());
			docenteDto.setCognome(utente.getCognome());
			docenteDto.setEmail(utente.getEmail());
			docenteDto.setGrado(docenti.get(i).getGrado());
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			//docenteDto.setDataNascita(formatter.parse(utente.getDataNascita()));
			docenteDto.setDataNascita(utente.getDataNascita());
			docenteDto.setStipendio(docenti.get(i).getStipendio());
			docentiDto.add(i, docenteDto);			
		}
		return docentiDto;
	}	
		
	@GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public DocenteDto getById(@PathVariable("id") int id) throws DocenteNotFoundException, UtenteNotFoundException {
		Docente docente = new Docente();
		Utente utente = new Utente();
		docente=docenteService.getById(id);
		int idUtente = docente.getUtente().getIdutente();
		utente = utenteService.getById(idUtente);
		DocenteDto docenteDto = new DocenteDto();
		docenteDto.setNome(utente.getNome());
		docenteDto.setCognome(utente.getCognome());
		docenteDto.setStipendio(docente.getStipendio());		
		return docenteDto;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Docente post(@RequestBody DocenteDto docenteDto) throws UtenteNotFoundException, DocenteNotFoundException, ParseException {
		
		Utente user = new Utente();
		Docente doc = new Docente();
		NumeroTelefono num = new NumeroTelefono();

		user.setNome(docenteDto.getNome());
		user.setCognome(docenteDto.getCognome());
		user.setEmail(docenteDto.getEmail());
		user.setIdOrigin(0);
		user.setPassword(docenteDto.getNome()+"pass");
		user.setDataNascita(docenteDto.getDataNascita());
		utenteService.save(user);	
		
		num.setNumeroTelefono(docenteDto.getNumeroTelefono());
		num.setUtente(user);
		numeroService.save(num);
		
		doc.setGrado(docenteDto.getGrado());
		doc.setStipendio(docenteDto.getStipendio());
		doc.setUtente(user);
		return docenteService.save(doc);
	}
	
}
