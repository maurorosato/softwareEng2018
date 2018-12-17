package it.unisalento.se.saw.restapi;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.dto.ValutazioneDto;

@RestController()
@RequestMapping(value="/valutazione")
public class ValutazioneRestController {

	public ValutazioneRestController() {
		super();
	}
	
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody ValutazioneDto valutazioneDto) {
		System.out.println(valutazioneDto.getId());
		System.out.println(valutazioneDto.getIdStudente());
		System.out.println(valutazioneDto.getOggettoInteressato());
	}
}
