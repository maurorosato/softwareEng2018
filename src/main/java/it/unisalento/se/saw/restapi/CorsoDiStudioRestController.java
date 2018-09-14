package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.converter.CorsoDiStudioConverter;
import it.unisalento.se.saw.domain.CorsoDiStudio;

import it.unisalento.se.saw.dto.CorsoDiStudioDto;

import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;


@RestController()
@RequestMapping(value="/corso")
public class CorsoDiStudioRestController {

	@Autowired
	ICorsoDiStudioService corsoDiStudioService;
	
	public CorsoDiStudioRestController() {
		super();
	}
	
	public CorsoDiStudioRestController(ICorsoDiStudioService corsoDiStudioService) {
		this.corsoDiStudioService = corsoDiStudioService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CorsoDiStudioDto> getAll() throws CorsoDiStudioNotFoundException {
		List<CorsoDiStudioDto> corsiDto= new ArrayList<CorsoDiStudioDto>();
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());
		Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();

		while (corsoIterator.hasNext()){
			CorsoDiStudioDto corsoDto = new CorsoDiStudioDto();
			CorsoDiStudio corso = corsoIterator.next();

			corsoDto = CorsoDiStudioConverter.domainToDto(corso);
			corsiDto.add(corsoDto);
		}
	
		return corsiDto;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public CorsoDiStudio post(@RequestBody CorsoDiStudioDto corsoDto) throws CorsoDiStudioNotFoundException{	
	
		CorsoDiStudio corsoSave = new CorsoDiStudio();
		corsoSave = CorsoDiStudioConverter.dtoToDomain(corsoDto);
		
		return corsoDiStudioService.save(corsoSave);
	}
	
	@GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public CorsoDiStudioDto getById(@PathVariable("id") int id) throws CorsoDiStudioNotFoundException {
		CorsoDiStudio corso=new CorsoDiStudio();
		corso=corsoDiStudioService.getById(id);
		CorsoDiStudioDto corsoDto = new CorsoDiStudioDto();
		corsoDto = CorsoDiStudioConverter.domainToDto(corso);
		
		return corsoDto;
	}
	
}
