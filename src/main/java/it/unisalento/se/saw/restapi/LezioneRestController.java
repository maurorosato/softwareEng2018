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
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.LezioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;

@RestController()
@RequestMapping(value="/lezione")
public class LezioneRestController {

	@Autowired
	ILezioneService lezioneService;
	
	@Autowired
	IAulaService aulaService;
	
	/*@Autowired
	IAulaPrenotazioneEventoService aulaPrenotazioneEventoService;*/
	
	public LezioneRestController() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public LezioneRestController(ILezioneService lezioneService) {
		this.lezioneService = lezioneService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<LezioneDto> getAll() throws LezioneNotFoundException, AulaNotFoundException {
		List<LezioneDto> lezioniDto= new ArrayList<LezioneDto>();
		List<Lezione> lezioni = (lezioneService.getAll());	
		List<Aula> aule = (aulaService.getAll());
		//List<AulaPrenotazioneEvento> aulaPrenotazioneEvento = (aulaPrenotazioneEventoService.getAll());
		int idEvento = 0;
		int idAula = 0;
		String nomeAula = null;
		Date data = null;
		
		for (int i=0; i < lezioni.size(); i++){
			LezioneDto lezioneDto = new LezioneDto();
			lezioneDto.setGradimento(lezioni.get(i).getGradimento());
			lezioneDto.setIdEvento(lezioni.get(i).getEvento().getIdevento());
			idEvento = lezioni.get(i).getEvento().getIdevento();

			for (int t=0; t < aule.size(); t++){
				if(aule.get(t).getIdaula() == idAula){
					nomeAula = aule.get(t).getNome();
				}
			}
			lezioneDto.setAula(nomeAula);
			lezioneDto.setData(data);
			
			lezioniDto.add(i,lezioneDto);
		}
		return lezioniDto;
	}
}