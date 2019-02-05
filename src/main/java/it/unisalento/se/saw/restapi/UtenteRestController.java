package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.converter.IConverter;
import it.unisalento.se.saw.converter.UtenteConverter;
import it.unisalento.se.saw.converter.UtenteLoggatoConverter;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.UtenteDto;
import it.unisalento.se.saw.dto.UtenteLoggatoDto;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/utente")	
public class UtenteRestController {
	
	@Autowired
	IUtenteService utenteService;
	@Autowired
	IDocenteService docenteService;
	@Autowired
	IStudenteService studenteService;
	@Autowired
	ICorsoDiStudioService corsoDiStudioService;
	@Autowired
	INumeroTelefonoService numeroTelefonoService;

	IConverter utenteConverter = new UtenteConverter();
	public UtenteRestController() {
		super();
	}

	public UtenteRestController(IUtenteService utenteService, IDocenteService docenteService, IStudenteService studenteService, ICorsoDiStudioService corsoDiStudioService, INumeroTelefonoService numeroTelefonoService ) {
		this.utenteService = utenteService;
		this.docenteService = docenteService;
		this.studenteService = studenteService;
		this.corsoDiStudioService = corsoDiStudioService;
		this.numeroTelefonoService = numeroTelefonoService;
	}

	@RequestMapping(value="/login/{email}/{password}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public UtenteLoggatoDto login(@PathVariable("email") String email,@PathVariable("password") String password) throws UtenteNotFoundException, DocenteNotFoundException, StudenteNotFoundException, CorsoDiStudioNotFoundException, NumeroTelefonoNotFoundException {
		
		Utente u = utenteService.autenticazione(email,password);
		UtenteDto uDto = (UtenteDto) utenteConverter.domainToDto(u);		
		List<Utente> utenti = utenteService.getAll();
		List<Docente> docenti = docenteService.getAll();
		List<Studente> studenti = studenteService.getAll();
		List<CorsoDiStudio> corsi = corsoDiStudioService.getAll();
		List<NumeroTelefono> numeriTelefono = numeroTelefonoService.getAll();

		UtenteLoggatoDto uLoggatoDto = UtenteLoggatoConverter.domainToDto(uDto,utenti,studenti,docenti,corsi,numeriTelefono);
		
		return uLoggatoDto;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UtenteDto> getAll() throws UtenteNotFoundException {
		List<Utente> utenti = utenteService.getAll();
		List<UtenteDto> utentiDto= new ArrayList<UtenteDto>();

		Utente utente = new Utente();
		for(int i=0;i<utenti.size();i++) {
			UtenteDto utenteDto = new UtenteDto();
			utente= utenti.get(i);
			if(utente.getNome() != "default"){
				utenteDto.setIdutente(utente.getIdutente());
				utenteDto.setNome(utente.getNome());
				utenteDto.setCognome(utente.getCognome());
				utentiDto.add(i, utenteDto);
			}
		}
		return utentiDto;
	}

	@GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UtenteDto getById(@PathVariable("id") int id) throws UtenteNotFoundException{
		Utente utente=new Utente();
		utente=utenteService.getById(id);
		UtenteDto utenteDto = new UtenteDto();
		utenteDto.setNome(utente.getNome());
		utenteDto.setCognome(utente.getCognome());
		return utenteDto;
	}
}
