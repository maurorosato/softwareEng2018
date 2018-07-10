package it.unisalento.se.saw.restapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
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
	public List<CorsoDiStudio> getAll() throws CorsoDiStudioNotFoundException {
		return corsoDiStudioService.getAll();
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public CorsoDiStudio post(@RequestBody CorsoDiStudio corso) throws CorsoDiStudioNotFoundException{		
		return corsoDiStudioService.save(corso);
	}
}
