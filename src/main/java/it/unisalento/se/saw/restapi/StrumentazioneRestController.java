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
import it.unisalento.se.saw.Iservices.IStrumentazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.dto.StrumentazioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;


@RestController()
@RequestMapping(value="/strumentazione")
public class StrumentazioneRestController {
	
	@Autowired
	IStrumentazioneService strumentazioneService;
	
	@Autowired
	IAulaService aulaService;
	
	
	public StrumentazioneRestController() {
		super();
	}
	
	public StrumentazioneRestController(IStrumentazioneService strumentazioneService) {
		this.strumentazioneService= strumentazioneService;
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StrumentazioneDto> getAll() throws StrumentazioneNotFoundException {
		List<StrumentazioneDto> strumentazioniDto = new ArrayList<StrumentazioneDto>();;
		List<Strumentazione> strumenti = null;
		strumenti = strumentazioneService.getAll();
		for(int i=0;i<strumenti.size();i++) {
			StrumentazioneDto strumentazioneDto = new StrumentazioneDto();
			strumentazioneDto.setDescrizione(strumenti.get(i).getDescrizione());
			strumentazioneDto.setStato(strumenti.get(i).getStato());
			strumentazioneDto.setIdAulaRiferimento(strumenti.get(i).getAula().getIdaula());
			strumentazioniDto.add(i, strumentazioneDto);	
		}
		return strumentazioniDto;
	}	
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody StrumentazioneDto strumentazioneDto) throws StrumentazioneNotFoundException, ParseException, AulaNotFoundException {
		
		Strumentazione str = new Strumentazione();
		Aula aula = new Aula();
		/****/
		aula  = aulaService.getById(strumentazioneDto.getIdAulaRiferimento());
		str.setDescrizione(strumentazioneDto.getDescrizione());
		str.setStato(strumentazioneDto.getStato());	
		str.setAula(aula);
		strumentazioneService.save(str);
	}
}
