package it.unisalento.se.saw.restapi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.converter.IConverter;
import it.unisalento.se.saw.converter.StudenteConverter;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;
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
	
	@Autowired
	INumeroTelefonoService numeroService;
	
	IConverter studenteConverter = new StudenteConverter();
	
	public StudenteRestController() {
		super();
	}
	
	public StudenteRestController(IStudenteService studenteService, IUtenteService utenteService, ICorsoDiStudioService corsoDiStudioService, INumeroTelefonoService numService) {
		this.studenteService = studenteService;
		this.corsoDiStudioService = corsoDiStudioService;
		this.numeroService = numService;
		this.utenteService = utenteService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StudenteDto> getAll() throws StudenteNotFoundException, UtenteNotFoundException, CorsoDiStudioNotFoundException, NumeroTelefonoNotFoundException {
		List<StudenteDto> studentiDto= new ArrayList<StudenteDto>();
		List<Utente> utenti = utenteService.getAll();
		List<Studente> studenti = studenteService.getAll();
		List<CorsoDiStudio> corsi = corsoDiStudioService.getAll();
		List<NumeroTelefono> numeriTelefono = numeroService.getAll();

		int idUtente;
		int idCorso;
		String nomeCorso = null;
		String numTelefono = "- ";
		
		Iterator<Studente> studenteIterator = studenti.iterator();
		while(studenteIterator.hasNext()){
			Studente studente = studenteIterator.next();
			StudenteDto studenteDto = new StudenteDto();
			if(studente.getIdstudente() != 1){
				studenteDto = (StudenteDto) studenteConverter.domainToDto(studente);
				
				Iterator<NumeroTelefono> numeroIterator = numeriTelefono.iterator();
				while (numeroIterator.hasNext()){
					NumeroTelefono num = numeroIterator.next();
					if(num.getUtente().getIdutente() == studenteDto.getIdUserStudente()){
						numTelefono = numTelefono + num.getNumeroTelefono() + "- ";
					}
					studenteDto.setNumeroTelefono(numTelefono);
				}	
				
				Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
				while (corsoIterator.hasNext()){
					CorsoDiStudio c = corsoIterator.next();
					if(c.getIdcorsoDiStudio() == studente.getCorsoDiStudioIdcorsoDiStudio()){
						studenteDto.setCorsoDiStudio(c.getNomeCorso());
					}
				}
				
				//studenteDto = StudenteConverter.domainToDto(studente, /*utenti,*/ corsi,numeriTelefono);
				studentiDto.add(studenteDto);
			}
		}
/*
		for(int i=0;i<studenti.size();i++) {
			StudenteDto studenteDto = new StudenteDto();
			Studente stud = studenti.get(i);
			if (stud.getIdstudente()!= 1){
				idUtente = studenti.get(i).getUtente().getIdutente();
				Utente utente = new Utente();
				utente = utenteService.getById(idUtente);	
				studenteDto.setIdUserStudente(studenti.get(i).getUtente().getIdutente());
				studenteDto.setIdStudente(stud.getIdstudente());
				studenteDto.setNome(utente.getNome());
				studenteDto.setCodiceFiscale(stud.getCodiceFiscale());
				studenteDto.setCognome(utente.getCognome());
				studenteDto.setDataNascita(utente.getDataNascita());
				studenteDto.setEmail(utente.getEmail());
				
				for (int j=0; j<numeriTelefono.size(); j++){
					if (numeriTelefono.get(j).getUtente().getIdutente() == idUtente){
						numTelefono = numTelefono + numeriTelefono.get(j).getNumeroTelefono() + " - ";
					}
				}
				
				idCorso = studenti.get(i).getCorsoDiStudioIdcorsoDiStudio();
				for(int j=0; j< corsi.size(); j++ ){
					if(idCorso == corsi.get(j).getIdcorsoDiStudio()){
						nomeCorso = corsi.get(j).getNomeCorso();
					}
				}
				
				studenteDto.setMatricola(stud.getMatricola());
				studenteDto.setIndirizzo(stud.getIndirizzo());
				studenteDto.setNazione(stud.getNazione());
				studenteDto.setNumeroTelefono(numTelefono);
				studenteDto.setIdCorsoDiStudio(idCorso);
				studenteDto.setCorsoDiStudio(nomeCorso);
				numTelefono = "- ";
			}
			studentiDto.add(i, studenteDto);			
		}
*/
		return studentiDto;	
	}
	
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Studente post(@RequestBody StudenteDto studenteDto) throws StudenteNotFoundException, ParseException, CorsoDiStudioNotFoundException {
		Utente user = new Utente();
		Studente stud = new Studente();
		NumeroTelefono num = new NumeroTelefono();
		String nomeCorso;
		int idCorso = 0;

		List<CorsoDiStudio> corsi = null;
		corsi = corsoDiStudioService.getAll();
		
		user.setNome(studenteDto.getNome());
		user.setCognome(studenteDto.getCognome());
		user.setEmail(studenteDto.getEmail());
		user.setIdOrigin(0);
		user.setPassword(studenteDto.getNome()+"pass");
		user.setDataNascita(studenteDto.getDataNascita());
		utenteService.save(user);
		
		num.setNumeroTelefono(studenteDto.getNumeroTelefono());
		num.setUtente(user);
		numeroService.save(num);
		
		stud.setCodiceFiscale(studenteDto.getCodiceFiscale());
		stud.setIndirizzo(studenteDto.getIndirizzo());
		stud.setMatricola(studenteDto.getMatricola());
		stud.setNazione(studenteDto.getNazione());
		
		nomeCorso = studenteDto.getCorsoDiStudio();
		
		for(int j=0; j< corsi.size(); j++ ){
			if(nomeCorso.equals(corsi.get(j).getNomeCorso())){
				idCorso = corsi.get(j).getIdcorsoDiStudio();
			}
		}
		stud.setCorsoDiStudioIdcorsoDiStudio(idCorso);		
		stud.setUtente(user);
		
		return studenteService.save(stud);
	}

	@PatchMapping (value = "/aggiornaStudente",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void aggiornaStudente(@RequestBody StudenteDto studenteDto) throws StudenteNotFoundException {
		studenteService.aggiornaStudente(studenteDto);
	}
	
	@RequestMapping(value="/getStudenteIscrittoInsegnamento/{idInsegnamento}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StudenteDto> getStudenteIscrittoInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws StudenteNotFoundException, UtenteNotFoundException, CorsoDiStudioNotFoundException, NumeroTelefonoNotFoundException {
		List<StudenteDto> studentiDto= new ArrayList<StudenteDto>();
		List<NumeroTelefono> numeriTelefono = numeroService.getAll();
		String numTelefono = "- ";

		Insegnamento insegnamento = new Insegnamento();
		insegnamento.setIdinsegnamento(idInsegnamento);
		List<Studente> studenti = studenteService.getStudenteIscrittoInsegnamento(insegnamento);
		List<CorsoDiStudio> corsi = corsoDiStudioService.getAll();
		List<Utente> utenti =utenteService.getAll();
		Iterator<Studente> studenteIterator = studenti.iterator();
		
		while(studenteIterator.hasNext()){
			StudenteDto studenteDto = new StudenteDto();
			Studente studente = studenteIterator.next();
			studenteDto = (StudenteDto) studenteConverter.domainToDto(studente);
			
			Iterator<NumeroTelefono> numeroIterator = numeriTelefono.iterator();
			while (numeroIterator.hasNext()){
				NumeroTelefono num = numeroIterator.next();
				if(num.getUtente().getIdutente() == studenteDto.getIdUserStudente()){
					numTelefono = numTelefono + num.getNumeroTelefono() + "- ";
				}
				studenteDto.setNumeroTelefono(numTelefono);
			}	
			
			Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
			while (corsoIterator.hasNext()){
				CorsoDiStudio c = corsoIterator.next();
				if(c.getIdcorsoDiStudio() == studente.getCorsoDiStudioIdcorsoDiStudio()){
					studenteDto.setCorsoDiStudio(c.getNomeCorso());
				}
			}
			//studenteDto = StudenteConverter.domainToDto(studente,/* utenti,*/ corsi,numeriTelefono);
			studentiDto.add(studenteDto);
		}
		return studentiDto;
	}
}

