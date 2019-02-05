package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.ISegreteriaService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.SegreteriaDidattica;
import it.unisalento.se.saw.dto.SegreteriaDto;
import it.unisalento.se.saw.exceptions.SegreteriaNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;

@RestController()
@RequestMapping(value="/segreteria")	
public class SegreteriaDidatticaRestController {

	@Autowired
	IUtenteService utenteService;

	@Autowired
	ISegreteriaService segreteriaService;
	
	public SegreteriaDidatticaRestController() {
		super();
	}
	
	public SegreteriaDidatticaRestController(ISegreteriaService segreteriaService, IUtenteService utenteService) {
		this.segreteriaService = segreteriaService;
		this.utenteService = utenteService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SegreteriaDto> getAll() throws SegreteriaNotFoundException, UtenteNotFoundException{
		List<SegreteriaDto> segretariDto= new ArrayList<SegreteriaDto>();
		List<SegreteriaDidattica> segretari = segreteriaService.getAll();

		Iterator<SegreteriaDidattica> segreteriaIterator = segretari.iterator();
		while(segreteriaIterator.hasNext()){
			SegreteriaDidattica segreteria = segreteriaIterator.next();
			SegreteriaDto segreteriaDto = new SegreteriaDto();
			segreteriaDto.setIdUserSegretario(segreteria.getUtente().getIdutente());
			segreteriaDto.setRuolo(segreteria.getRuolo());
			segreteriaDto.setStipendio(segreteria.getStipendio());
			segretariDto.add(segreteriaDto);
		}
		return segretariDto;	
	}
	
}

