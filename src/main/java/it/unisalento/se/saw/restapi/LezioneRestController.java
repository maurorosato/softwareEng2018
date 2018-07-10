package it.unisalento.se.saw.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;

@RestController()
@RequestMapping(value="/lezione")
public class LezioneRestController {

	@Autowired
	ILezioneService lezioneService;
	
	public LezioneRestController() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public LezioneRestController(ILezioneService lezioneService) {
		this.lezioneService = lezioneService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Lezione> getAll() throws LezioneNotFoundException {
		return lezioneService.getAll();
	}
	/*
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Lezione post(@RequestBody Lezione corso) throws LezioneNotFoundException{		
		return lezioneService.save(corso);
	}
	*/
}