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
import it.unisalento.se.saw.converter.AulaConverter;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.dto.AulaDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;

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
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AulaDto> getAll() throws AulaNotFoundException {
		 	
		List<AulaDto> auleDto= new ArrayList<AulaDto>();
		List<Aula> aule = (aulaService.getAll());
		Iterator<Aula> aulaIterator = aule.iterator();
		
		while (aulaIterator.hasNext()){
			AulaDto aulaDto = new AulaDto();
			Aula aula = aulaIterator.next();
			
			aulaDto = AulaConverter.domainToDto(aula);
			auleDto.add(aulaDto);
		}
		return auleDto;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Aula post(@RequestBody AulaDto aulaDto) throws AulaNotFoundException, ParseException {
		Aula aulaSave = new Aula();
		
		aulaSave = AulaConverter.dtoToDomain(aulaDto);
		return aulaService.save(aulaSave);	
	}
	
	@PatchMapping (value = "/aggiornaAula",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void aggiornaAula(@RequestBody AulaDto aulaDto) throws AulaNotFoundException {
		aulaService.aggiornaAula(aulaDto);
	}
	
	@PatchMapping (value = "/localizzaAula",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void localizzaAula(@RequestBody AulaDto aulaDto) throws AulaNotFoundException {
		aulaService.localizzaAula(aulaDto);
	}
	
	@RequestMapping (value = "/rimuoviAula/{idAula}", method = RequestMethod.DELETE)
	public void rimuoviAula(@PathVariable("idAula") int idAula) throws AulaNotFoundException {
		aulaService.rimuoviAula(idAula);
	}

	@GetMapping(value="/getAuleLibere/{datainizio}/{datafine}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AulaDto> getAuleLibere(@PathVariable("datainizio") Date datainizio,@PathVariable("datafine") Date datafine) throws AulaNotFoundException,ParseException   {
		List<AulaDto> auleDto= new ArrayList<AulaDto>();
		List<Aula> aule = (aulaService.getAuleLibere(datainizio,datafine));
		Iterator<Aula> aulaIterator = aule.iterator();
		
		while (aulaIterator.hasNext()){
			AulaDto aulaDto = new AulaDto();
			Aula aula = aulaIterator.next();
			
			aulaDto = AulaConverter.domainToDto(aula);
			auleDto.add(aulaDto);
		}
		return auleDto;
	}	
}

