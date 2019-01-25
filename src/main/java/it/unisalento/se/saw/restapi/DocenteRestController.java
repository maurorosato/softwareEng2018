package it.unisalento.se.saw.restapi;

import java.text.ParseException;
import java.util.ArrayList;
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

import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.converter.DocenteConverter;
import it.unisalento.se.saw.converter.UtenteConverter;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.DocenteDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/docente")	
public class DocenteRestController {

	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	IUtenteService utenteService;
	
	@Autowired
	INumeroTelefonoService numeroTelefonoService;
	
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
	public List<DocenteDto> getAll() throws DocenteNotFoundException, UtenteNotFoundException, NumeroTelefonoNotFoundException {
		List<DocenteDto> docentiDto= new ArrayList<DocenteDto>();
		List<Docente> docenti = docenteService.getAll();
		List<NumeroTelefono> numeriTelefono = numeroTelefonoService.getAll();
		List<Utente> utenti = utenteService.getAll();
		//List<NumeroTelefono> numeriTelefono = numeroService.getAll();
		//int idUtente;
		//String numTelefono = "- ";
		
		Iterator<Docente> docenteIterator = docenti.iterator();
		while(docenteIterator.hasNext()){
			Docente docente = docenteIterator.next();
			DocenteDto docenteDto = new DocenteDto();

			if (docente.getIddocente() != 1){
				docenteDto = DocenteConverter.domainToDto(docente/*,utenti*/, numeriTelefono);
				docentiDto.add(docenteDto);
			}
		}
/*
		for(int i=0;i<docenti.size();i++) {
			DocenteDto docenteDto = new DocenteDto();
			if ( docenti.get(i).getIddocente()!=1){
				idUtente = docenti.get(i).getUtente().getIdutente();
				
				for (int j=0; j<numeriTelefono.size(); j++){
					if (numeriTelefono.get(j).getUtente().getIdutente() == idUtente){
						numTelefono = numTelefono + numeriTelefono.get(j).getNumeroTelefono() + " - ";
					}
				}
				
				Utente utente = new Utente();
				utente = utenteService.getById(idUtente);
				docenteDto.setIdDocente(docenti.get(i).getIddocente());
				docenteDto.setNome(utente.getNome());
				docenteDto.setCognome(utente.getCognome());
				docenteDto.setEmail(utente.getEmail());
				docenteDto.setGrado(docenti.get(i).getGrado());
				docenteDto.setDataNascita(utente.getDataNascita());
				docenteDto.setStipendio(docenti.get(i).getStipendio());
				docenteDto.setNumeroTelefono(numTelefono);
				numTelefono = "- ";
				
				docentiDto.add(i, docenteDto);
			}
						
		}
*/
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
		numeroTelefonoService.save(num);
		
		doc.setGrado(docenteDto.getGrado());
		doc.setStipendio(docenteDto.getStipendio());
		doc.setUtente(user);
		return docenteService.save(doc);
	}
	
	@PatchMapping (value = "/aggiornaDocente",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void aggiornaDocente(@RequestBody DocenteDto docenteDto) throws DocenteNotFoundException {
		docenteService.aggiornaDocente(docenteDto);
	}
}
