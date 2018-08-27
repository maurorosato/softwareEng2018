package it.unisalento.se.saw.restapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.ISegnalazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.SegnalazioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.services.AulaService;

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
	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SegnalazioneDto> getAll() throws SegnalazioneNotFoundException, AulaNotFoundException {
		List<SegnalazioneDto> segnalazioniDto= new ArrayList<SegnalazioneDto>();
		List<Segnalazione> segnalazioni = null;
		segnalazioni = (segnalazioneService.getAll());
		
		List<Aula> aule = null;
		aule = (aulaService.getAll());
		
		for(int i=0;i<segnalazioni.size();i++) {
			SegnalazioneDto segnalazioneDto = new SegnalazioneDto();
			String nomeAula = null;
			for(int j=0; j < aule.size(); j++){
				if (segnalazioni.get(i).getAula().getIdaula() == aule.get(j).getIdaula()){
					nomeAula = aule.get(j).getNome();
					}
			}
			segnalazioneDto.setOggettoInteressato(segnalazioni.get(i).getOggettoInteressato());
			segnalazioneDto.setMotivazione(segnalazioni.get(i).getMotivazione());
			segnalazioneDto.setStatoSegnalazione(segnalazioni.get(i).getStatoSegnalazione());
			segnalazioneDto.setNomeAula(nomeAula);
			segnalazioneDto.setData((Date) segnalazioni.get(i).getData());
			
			segnalazioniDto.add(i, segnalazioneDto);
		}
		
		return segnalazioniDto;
	}
}