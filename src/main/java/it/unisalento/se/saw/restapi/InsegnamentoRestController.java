package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.converter.InsegnamentoConverter;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.InsegnamentoDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;

@RestController()
@RequestMapping(value="/insegnamento")
public class InsegnamentoRestController {
	
	@Autowired
	IInsegnamentoService insegnamentoService;
	
	@Autowired
	ICorsoDiStudioService corsoDiStudioService;
	
	@Autowired
	IDocenteService docenteService;
	
	public InsegnamentoRestController() {
		super();
	}
	
	public InsegnamentoRestController(IInsegnamentoService insegnamentoService) {
		this.insegnamentoService= insegnamentoService;
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InsegnamentoDto> getAll() throws InsegnamentoNotFoundException, CorsoDiStudioNotFoundException, DocenteNotFoundException {
		List<InsegnamentoDto> insegnamentiDto= new ArrayList<InsegnamentoDto>();
		
		List<Insegnamento> insegnamenti = (insegnamentoService.getAll());
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());
		List<Docente> docenti = (docenteService.getAll());

		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
		
		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
			if (insegnamento.getIdinsegnamento()!=1){
				insegnamentoDto = InsegnamentoConverter.domainToDto(insegnamento,corsi,docenti);
				insegnamentiDto.add(insegnamentoDto);
			}
	
		}

		return insegnamentiDto;
	}
	
	@RequestMapping(value="/getAllInsegnamentiCorso/{idCorso}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InsegnamentoDto> getAllInsegnamentiCorso(@PathVariable("idCorso") int idCorso) throws InsegnamentoNotFoundException, CorsoDiStudioNotFoundException, DocenteNotFoundException {
		List<InsegnamentoDto> insegnamentiDto= new ArrayList<InsegnamentoDto>();
		List<CorsoDiStudio> corsi = corsoDiStudioService.getAll();
		List<Docente> docenti = docenteService.getAll();
		
		List<Insegnamento> insegnamenti = insegnamentoService.getAllInsegnamentiCorso(idCorso);
		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
		
		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
			insegnamentoDto = InsegnamentoConverter.domainToDto(insegnamento,corsi,docenti);
			
			insegnamentiDto.add(insegnamentoDto);	
		}
/*
		for(int i=0; i < insegnamenti.size(); i++){
			InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
			
			switch (insegnamenti.get(i).getAnno()){
			case 1:
				insegnamentoDto.setAnnoCorso("Primo anno");
				break;	
			case 2:
				insegnamentoDto.setAnnoCorso("Secondo anno");
				break;
			case 3:
				insegnamentoDto.setAnnoCorso("Terzo anno");
				break;
			}
				
			insegnamentoDto.setIdInsegnamento(insegnamenti.get(i).getIdinsegnamento());
			insegnamentoDto.setNome(insegnamenti.get(i).getNome());
			insegnamentoDto.setCfu(insegnamenti.get(i).getCfu());
			
			for (int j=0; j < corsi.size(); j++){
				if(insegnamenti.get(i).getCorsoDiStudioIdcorsoDiStudio() == corsi.get(j).getIdcorsoDiStudio()){
					insegnamentoDto.setCorsoDiStudio(corsi.get(j).getNomeCorso());
				}
			}		
			for (int t=0; t < docenti.size(); t++){
				if(insegnamenti.get(i).getDocente().getIddocente() == docenti.get(t).getIddocente()){
					insegnamentoDto.setDocente(docenti.get(t).getUtente().getNome() +" "+ docenti.get(t).getUtente().getCognome() );
					insegnamentoDto.setIdUserDocente(docenti.get(t).getUtente().getIdutente());
				}
			}
			
			insegnamentiDto.add(i, insegnamentoDto);	
		}
*/		
		return insegnamentiDto;
	}
	@RequestMapping(value="/getAllInsegnamentiDocente/{idUserDocente}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InsegnamentoDto> getAllInsegnamentiDocente(@PathVariable("idUserDocente") int idUserDocente) throws InsegnamentoNotFoundException, CorsoDiStudioNotFoundException, DocenteNotFoundException {
		
		List<InsegnamentoDto> insegnamentiDto= new ArrayList<InsegnamentoDto>();
		List<CorsoDiStudio> corsi = corsoDiStudioService.getAll();
		List<Docente> docenti = docenteService.getAll();
		Docente docente = new Docente();
		
		Iterator<Docente> docenteIterator = docenti.iterator();
		while (docenteIterator.hasNext()){
			Docente docenteI = docenteIterator.next();
			if(docenteI.getUtente().getIdutente() == idUserDocente){
				docente.setIddocente(docenteI.getIddocente());
			}
		}
		List<Insegnamento> insegnamenti = insegnamentoService.getAllInsegnamentiDocente(docente);
		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
			insegnamentoDto = InsegnamentoConverter.domainToDto(insegnamento,corsi,docenti);
			
			insegnamentiDto.add(insegnamentoDto);	
		}
		return insegnamentiDto;
	}
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody InsegnamentoDto insegnamentoDto) throws CorsoDiStudioNotFoundException, DocenteNotFoundException{
		
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());
		List<Docente> docenti = (docenteService.getAll());
		Insegnamento insegnamento = new Insegnamento();

		insegnamento = InsegnamentoConverter.dtoToDomain(insegnamentoDto, docenti, corsi);
		insegnamentoService.save(insegnamento);
	}
	
	@PatchMapping (value = "/aggiornaInsegnamento",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void aggiornaInsegnamento(@RequestBody InsegnamentoDto insegnamentoDto) throws AulaNotFoundException, InsegnamentoNotFoundException {
		insegnamentoService.aggiornaInsegnamento(insegnamentoDto);
	}
	
	@RequestMapping (value = "/rimuoviInsegnamento/{idInsegnamento}", method = RequestMethod.DELETE)
	public void rimuoviInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws InsegnamentoNotFoundException {
		insegnamentoService.rimuoviInsegnamento(idInsegnamento);
	}
}
