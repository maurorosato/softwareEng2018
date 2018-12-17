package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.Iservices.IMaterialeDidatticoService;
import it.unisalento.se.saw.converter.MaterialeDidatticoConverter;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.dto.MaterialeDidatticoDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.exceptions.MaterialeDidatticoNotFoundException;

@RestController()
@RequestMapping(value="/materialeDidattico")
public class MaterialeDidatticoRestController {
	
	@Autowired
	IEventoService eventoService;
	
	@Autowired
	ILezioneService lezioneService;
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	IMaterialeDidatticoService materialeDidatticoService;
	
	public MaterialeDidatticoRestController() {
		super();
	}
	
	public MaterialeDidatticoRestController(IMaterialeDidatticoService materialeDidatticoService) {
		this.materialeDidatticoService = materialeDidatticoService;
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MaterialeDidatticoDto> getAll() throws MaterialeDidatticoNotFoundException, LezioneNotFoundException, DocenteNotFoundException {
		
		List<MaterialeDidatticoDto> materialeDidatticoListaDto = new ArrayList<MaterialeDidatticoDto>();
		List<MaterialeDidattico> materialeDidattico = materialeDidatticoService.getAll();
		List<Lezione> lezioni = lezioneService.getAll();
		List<Docente> docenti = docenteService.getAll();

		Iterator<MaterialeDidattico> matDidatticoIterator = materialeDidattico.iterator();
		while(matDidatticoIterator.hasNext()){
			MaterialeDidattico matDidattico = matDidatticoIterator.next();
			MaterialeDidatticoDto materialeDidatticoDto = MaterialeDidatticoConverter.domainToDto(matDidattico,lezioni,docenti);
			
			materialeDidatticoListaDto.add(materialeDidatticoDto);
		}	
		return materialeDidatticoListaDto;
	}
	
	@RequestMapping(value="/getAllLezione/{idEvento}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MaterialeDidatticoDto> getAllLezione(@PathVariable("idEvento") int idEvento) throws MaterialeDidatticoNotFoundException, LezioneNotFoundException, DocenteNotFoundException {
		
		List<MaterialeDidatticoDto> materialeDidatticoListaDto = new ArrayList<MaterialeDidatticoDto>();
		List<Lezione> lezioni = lezioneService.getAll();
		List<Docente> docenti = docenteService.getAll();

		Lezione lezione = new Lezione();
		Iterator<Lezione> lezioneIterator = lezioni.iterator();
		while(lezioneIterator.hasNext()){
			Lezione lez = lezioneIterator.next();
			if (lez.getEvento().getIdevento() == idEvento){
				lezione.setIdlezione(lez.getIdlezione());			}
		}
		System.out.println(lezione.getIdlezione());
		List<MaterialeDidattico> materialeDidattico = materialeDidatticoService.getAllLezione(lezione);

		Iterator<MaterialeDidattico> matDidatticoIterator = materialeDidattico.iterator();
		while(matDidatticoIterator.hasNext()){
			MaterialeDidattico matDidattico = matDidatticoIterator.next();
			MaterialeDidatticoDto materialeDidatticoDto = MaterialeDidatticoConverter.domainToDto(matDidattico,lezioni,docenti);
			
			materialeDidatticoListaDto.add(materialeDidatticoDto);
		}
		return materialeDidatticoListaDto;
	}
	
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public MaterialeDidattico post(@RequestBody MaterialeDidatticoDto materialeDidatticoDto) throws LezioneNotFoundException, EventoNotFoundException, DocenteNotFoundException {

		List<Lezione> lezioni = lezioneService.getAll();
		List<Docente> docenti = docenteService.getAll();
		MaterialeDidattico materialeDidattico = new MaterialeDidattico();
		materialeDidattico = MaterialeDidatticoConverter.dtoToDomain(materialeDidatticoDto,lezioni,docenti);
		
		return materialeDidatticoService.save(materialeDidattico);
	}

}
