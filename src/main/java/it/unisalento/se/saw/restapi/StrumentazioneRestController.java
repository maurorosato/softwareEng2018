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

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IStrumentazioneService;
import it.unisalento.se.saw.converter.StrumentazioneConverter;
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
				
		List<StrumentazioneDto> strumentazioniDto = new ArrayList<StrumentazioneDto>();
		List<Strumentazione> strumenti = strumentazioneService.getAll();
		Iterator<Strumentazione> strumentazioneIterator = strumenti.iterator();
		
		while (strumentazioneIterator.hasNext()){
			StrumentazioneDto strumentazioneDto;
			Strumentazione strumentazione = strumentazioneIterator.next();
			
			strumentazioneDto= StrumentazioneConverter.domainToDto(strumentazione);
			strumentazioniDto.add(strumentazioneDto);	
		}
		return strumentazioniDto;
	}	
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody StrumentazioneDto strumentazioneDto) throws StrumentazioneNotFoundException, ParseException, AulaNotFoundException {
		List<Aula> aule = aulaService.getAll();
		Strumentazione str = new Strumentazione();
		Aula aula = new Aula();
		Iterator<Aula> aulaIterator = aule.iterator();
		int idAula = 0;

		while(aulaIterator.hasNext()){
			Aula aulaIter = aulaIterator.next();
			if ((aulaIter.getNome()).equals(strumentazioneDto.getAulaRiferimento())){
				idAula = aulaIter.getIdaula();
			}
		}
		
		aula  = aulaService.getById(idAula);
		str = StrumentazioneConverter.dtoToDomain(strumentazioneDto, aula);
		
		strumentazioneService.save(str);
	}
	
	@PatchMapping (value = "/aggiornaStrumentazione",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void aggiornaStrumentazione(@RequestBody StrumentazioneDto strumentazioneDto) throws StrumentazioneNotFoundException {
		strumentazioneService.aggiornaStrumentazione(strumentazioneDto);
	}
	
	@RequestMapping (value = "/rimuoviStrumentazione/{idStrumentazione}", method = RequestMethod.DELETE)
	public void rimuoviStrumentazione(@PathVariable("idStrumentazione") int idStrumentazione) throws StrumentazioneNotFoundException {
		strumentazioneService.rimuoviStrumentazione(idStrumentazione);
	}
}
