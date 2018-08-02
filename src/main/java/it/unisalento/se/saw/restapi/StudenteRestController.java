package it.unisalento.se.saw.restapi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/studente")	
public class StudenteRestController {

	@Autowired
	IUtenteService utenteService;
	
	@Autowired
	IStudenteService studenteService;
	
	@Autowired
	ICorsoDiStudioService corsoDiStudioService;
	
	public StudenteRestController() {
		super();
	}
	
	public StudenteRestController(IStudenteService studenteService) {
		this.studenteService = studenteService;
	}
	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StudenteDto> getAll() throws StudenteNotFoundException, UtenteNotFoundException {
		List<StudenteDto> studentiDto= new ArrayList<StudenteDto>();
		List<Studente> studenti = null;
		studenti = (studenteService.getAll());
		int idUtente;
		for(int i=0;i<studenti.size();i++) {
			StudenteDto studenteDto = new StudenteDto();
			idUtente = studenti.get(i).getUtente().getIdutente();
			Utente utente = new Utente();
			utente = utenteService.getById(idUtente);	
			Studente stud = studenti.get(i);
			studenteDto.setNome(utente.getNome());
			studenteDto.setCodiceFiscale(stud.getCodiceFiscale());
			studenteDto.setCognome(utente.getCognome());
			studenteDto.setDataNascita(utente.getDataNascita());
			studenteDto.setEmail(utente.getEmail());
			studenteDto.setIdCorso(stud.getCorsoDiStudioIdcorsoDiStudio());
			studenteDto.setMatricola(stud.getMatricola());
			studenteDto.setIndirizzo(stud.getIndirizzo());
			studenteDto.setLivello(stud.getLivello());
			studenteDto.setNazione(stud.getNazione());
			studentiDto.add(i, studenteDto);			
		}
		return studentiDto;	
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Studente post(@RequestBody StudenteDto studenteDto) throws StudenteNotFoundException, ParseException {
		Utente user = new Utente();
		Studente stud = new Studente();
		//CorsoDiStudio corso = new CorsoDiStudio();
		user.setNome(studenteDto.getNome());
		user.setCognome(studenteDto.getCognome());
		user.setEmail(studenteDto.getEmail());
		user.setIdOrigin(0);
		user.setPassword(studenteDto.getNome()+"pass");
		user.setDataNascita(studenteDto.getDataNascita());
		utenteService.save(user);	
		stud.setCodiceFiscale(studenteDto.getCodiceFiscale());
		stud.setIndirizzo(studenteDto.getIndirizzo());
		stud.setLivello(studenteDto.getLivello());
		stud.setMatricola(studenteDto.getMatricola());
		stud.setNazione(studenteDto.getNazione());
		stud.setCorsoDiStudioIdcorsoDiStudio(1);
		stud.setUtente(user);
		return studenteService.save(stud);
	}
}

