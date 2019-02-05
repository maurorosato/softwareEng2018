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
import it.unisalento.se.saw.converter.IConverter;
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
	
	IConverter insegnamentoConverter = (IConverter) new InsegnamentoConverter();

	
	public InsegnamentoRestController() {
		super();
	}
	
	public InsegnamentoRestController(IInsegnamentoService insegnamentoService,ICorsoDiStudioService corsoDiStudioService,IDocenteService docenteService) {
		this.insegnamentoService= insegnamentoService;
		this.corsoDiStudioService=corsoDiStudioService;
		this.docenteService=docenteService;
	}

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InsegnamentoDto> getAll() throws InsegnamentoNotFoundException, CorsoDiStudioNotFoundException, DocenteNotFoundException {
		
		List<InsegnamentoDto> insegnamentiDto= new ArrayList<InsegnamentoDto>();
		
		List<Insegnamento> insegnamenti = (insegnamentoService.getAll());
		List<Docente> docenti = (docenteService.getAll());
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());

		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();
		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
			if (insegnamento.getIdinsegnamento()!=1){
				insegnamentoDto = (InsegnamentoDto) insegnamentoConverter.domainToDto(insegnamento);
				
				Iterator<Docente> docenteIterator = docenti.iterator();
				while(docenteIterator.hasNext()){
					Docente docente = docenteIterator.next();
					if (insegnamento.getDocente().getIddocente() ==docente.getIddocente()){
						insegnamentoDto.setDocente(docente.getUtente().getNome() + ' ' + docente.getUtente().getCognome());
						insegnamentoDto.setIdUserDocente(docente.getUtente().getIdutente());
		
					}
				}				
				Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
				while(corsoIterator.hasNext()){
					CorsoDiStudio corso = corsoIterator.next();
					if (insegnamento.getCorsoDiStudioIdcorsoDiStudio() == corso.getIdcorsoDiStudio()){
						insegnamentoDto.setCorsoDiStudio(corso.getNomeCorso());
					}
				}
				switch (insegnamento.getAnno()){
				case 1:
					insegnamentoDto.setAnnoCorso("Primo Anno");
					break;
				case 2:
					insegnamentoDto.setAnnoCorso("Secondo Anno");
					break;
				case 3:
					insegnamentoDto.setAnnoCorso("Terzo Anno");
					break;
				}
				//insegnamentoDto = InsegnamentoConverter.domainToDto(insegnamento,corsi/*,docenti*/);
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
		System.out.println("sd"+insegnamenti.get(0).getCfu());

		Iterator<Insegnamento> insegnamentoIterator = insegnamenti.iterator();

		while(insegnamentoIterator.hasNext()){
			Insegnamento insegnamento = insegnamentoIterator.next();
			InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
			if (insegnamento.getIdinsegnamento()!=1){
				insegnamentoDto = (InsegnamentoDto) insegnamentoConverter.domainToDto(insegnamento);
				
				Iterator<Docente> docenteIterator = docenti.iterator();
				while(docenteIterator.hasNext()){
					Docente docente = docenteIterator.next();
					if (insegnamento.getDocente().getIddocente() ==docente.getIddocente()){
						insegnamentoDto.setDocente(docente.getUtente().getNome() + ' ' + docente.getUtente().getCognome());
						insegnamentoDto.setIdUserDocente(docente.getUtente().getIdutente());
		
					}
				}				
				Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
				while(corsoIterator.hasNext()){
					CorsoDiStudio corso = corsoIterator.next();
					if (insegnamento.getCorsoDiStudioIdcorsoDiStudio() == corso.getIdcorsoDiStudio()){
						insegnamentoDto.setCorsoDiStudio(corso.getNomeCorso());
					}
				}
				switch (insegnamento.getAnno()){
				case 1:
					insegnamentoDto.setAnnoCorso("Primo Anno");
					break;
				case 2:
					insegnamentoDto.setAnnoCorso("Secondo Anno");
					break;
				case 3:
					insegnamentoDto.setAnnoCorso("Terzo Anno");
					break;
				}
				//insegnamentoDto = InsegnamentoConverter.domainToDto(insegnamento,corsi/*,docenti*/);
				insegnamentiDto.add(insegnamentoDto);
			}
		}	
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
			
			if (insegnamento.getIdinsegnamento()!=1){
				insegnamentoDto = (InsegnamentoDto) insegnamentoConverter.domainToDto(insegnamento);
				
				Iterator<Docente> docIterator = docenti.iterator();
				while(docenteIterator.hasNext()){
					Docente doc = docIterator.next();
					if (insegnamento.getDocente().getIddocente() ==doc.getIddocente()){
						insegnamentoDto.setDocente(doc.getUtente().getNome() + ' ' + doc.getUtente().getCognome());
						insegnamentoDto.setIdUserDocente(doc.getUtente().getIdutente());
		
					}
				}				
				Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
				while(corsoIterator.hasNext()){
					CorsoDiStudio corso = corsoIterator.next();
					if (insegnamento.getCorsoDiStudioIdcorsoDiStudio() == corso.getIdcorsoDiStudio()){
						insegnamentoDto.setCorsoDiStudio(corso.getNomeCorso());
					}
				}
				switch (insegnamento.getAnno()){
				case 1:
					insegnamentoDto.setAnnoCorso("Primo Anno");
					break;
				case 2:
					insegnamentoDto.setAnnoCorso("Secondo Anno");
					break;
				case 3:
					insegnamentoDto.setAnnoCorso("Terzo Anno");
					break;
				}
				//insegnamentoDto = InsegnamentoConverter.domainToDto(insegnamento,corsi/*,docenti*/);
				insegnamentiDto.add(insegnamentoDto);
			}
		}
		return insegnamentiDto;
	}
	@PostMapping(value="save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void post(@RequestBody InsegnamentoDto insegnamentoDto) throws CorsoDiStudioNotFoundException, DocenteNotFoundException{
		
		List<CorsoDiStudio> corsi = (corsoDiStudioService.getAll());
		List<Docente> docenti = (docenteService.getAll());
		Insegnamento insegnamento = new Insegnamento();
		int anno = 0;

		insegnamento = (Insegnamento) insegnamentoConverter.dtoToDomain(insegnamentoDto);
		Iterator<Docente> docenteIterator = docenti.iterator();
		Docente docente = new Docente();

		while (docenteIterator.hasNext()){
			Docente docenteI = docenteIterator.next();
			if((docenteI.getUtente().getNome()+" "+docenteI.getUtente().getCognome()).equals(insegnamentoDto.getDocente())){
				docente.setIddocente(docenteI.getIddocente());
				insegnamento.setDocente(docente);
			}
		}
		
		Iterator<CorsoDiStudio> corsoIterator = corsi.iterator();
		while (corsoIterator.hasNext()){
			CorsoDiStudio corsoI = corsoIterator.next();
			if(corsoI.getNomeCorso().equals(insegnamentoDto.getCorsoDiStudio())){
				insegnamento.setCorsoDiStudioIdcorsoDiStudio(corsoI.getIdcorsoDiStudio());	
			}
		}
		
		switch (insegnamentoDto.getAnnoCorso()){
			case "Primo anno":
				anno = 1 ;
				break;	
			case "Secondo anno":
				anno = 2 ;
				break;
			case "Terzo anno":
				anno = 3 ;
				break;
		}
		insegnamento.setAnno(anno);
		
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
