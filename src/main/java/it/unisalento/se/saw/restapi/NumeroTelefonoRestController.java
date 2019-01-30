package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.dto.NumeroTelefonoDto;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;


@RestController()
@RequestMapping(value="/numeroTelefono")
public class NumeroTelefonoRestController {
	
	@Autowired
	INumeroTelefonoService numeroTelefonoService;
	
	public NumeroTelefonoRestController(INumeroTelefonoService numeroTelefonoService) {
		this.numeroTelefonoService = numeroTelefonoService;
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<NumeroTelefonoDto> getAll() throws NumeroTelefonoNotFoundException{
		List<NumeroTelefonoDto> numeriTelefonoDto= new ArrayList<NumeroTelefonoDto>();
		List<NumeroTelefono> numeriTelefono = numeroTelefonoService.getAll();
		Iterator<NumeroTelefono> numeroTelefonoIterator = numeriTelefono.iterator();
		
		while(numeroTelefonoIterator.hasNext()){
			NumeroTelefonoDto numeroTelefonoDto = new NumeroTelefonoDto();
			NumeroTelefono numeroTelefono = numeroTelefonoIterator.next();
			
			numeroTelefonoDto.setNumeroTelefono(numeroTelefono.getNumeroTelefono());
			numeroTelefonoDto.setIdUtente(numeroTelefono.getUtente().getIdutente());
			
			numeriTelefonoDto.add(numeroTelefonoDto);
		}
		return numeriTelefonoDto;

		
	}
}
