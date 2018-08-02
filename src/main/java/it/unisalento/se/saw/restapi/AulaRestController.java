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
import it.unisalento.se.saw.Iservices.IAulaService;
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
	/*
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Corso post(@RequestBody Corso corso) throws ParseException, CorsoNotFoundException {
		return corsoService.save(corso);
	}
	*/
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AulaDto> getAll() throws AulaNotFoundException {
		List<AulaDto> auleDto= new ArrayList<AulaDto>();
		List<Aula> aule = null;
		aule = (aulaService.getAll());
		for(int i=0;i<aule.size();i++) {
			AulaDto aulaDto = new AulaDto();
			aulaDto.setIdAula(aule.get(i).getIdaula());
			aulaDto.setEdificio(aule.get(i).getEdificio());
			aulaDto.setNome(aule.get(i).getNome());
			aulaDto.setStato(aule.get(i).getStato());
			auleDto.add(i, aulaDto);
		}
		return auleDto;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Aula post(@RequestBody AulaDto aulaDto) throws AulaNotFoundException, ParseException {
		Aula aulaSave = new Aula();
		aulaSave.setNome(aulaDto.getNome());
		aulaSave.setEdificio(aulaDto.getEdificio());
		aulaSave.setStato(aulaDto.getStato());
		aulaSave.setCapienza(aulaDto.getCapienza());
		return aulaService.save(aulaSave);	
	}
}

