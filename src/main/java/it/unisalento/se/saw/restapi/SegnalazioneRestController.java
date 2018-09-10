package it.unisalento.se.saw.restapi;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.ISegnalazioneService;
import it.unisalento.se.saw.converter.SegnalazioneAdaptee;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.dto.SegnalazioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;


@RestController()
@RequestMapping(value="/segnalazione")
public class SegnalazioneRestController {

	@Autowired
	ISegnalazioneService segnalazioneService;
	
	@Autowired
	IAulaService aulaService;
	
	public SegnalazioneRestController() {
		super();
	}
	
	public SegnalazioneRestController(ISegnalazioneService segnalazioneService) {
		this.segnalazioneService= segnalazioneService;
	}
	
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody SegnalazioneDto segnalazioneDto) throws ParseException, AulaNotFoundException{
		
		Segnalazione segnalazione;
		List<Aula> aule = aulaService.getAll();
		
		/************************************************************************************************/
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date(0);
		/************************************************************************************************/
		
		segnalazione = SegnalazioneAdaptee.dtoToDomain(segnalazioneDto, aule, 1, dateobj);
		
		segnalazioneService.save(segnalazione);
	}
	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SegnalazioneDto> getAll() throws SegnalazioneNotFoundException, AulaNotFoundException {
		List<SegnalazioneDto> segnalazioniDto= new ArrayList<SegnalazioneDto>();
		List<Segnalazione> segnalazioni = (segnalazioneService.getAll());
		List<Aula> aule = (aulaService.getAll());
		Iterator<Segnalazione> segnalazioneIterator = segnalazioni.iterator();
				
		while(segnalazioneIterator.hasNext()){
			SegnalazioneDto segnalazioneDto = new SegnalazioneDto();
			Segnalazione segnalazione = segnalazioneIterator.next();
			
			segnalazioneDto = SegnalazioneAdaptee.domainToDto(segnalazione, aule);	
			segnalazioniDto.add(segnalazioneDto);
		}
	
		return segnalazioniDto;
	}
}