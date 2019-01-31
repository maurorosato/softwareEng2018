package it.unisalento.se.saw.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.converter.InsegnamentoConverter;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.InsegnamentoDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.InsegnamentoNotFoundException;
import it.unisalento.se.saw.restapi.InsegnamentoRestController;

@RunWith(MockitoJUnitRunner.class)
public class InsegnamentoRestControllerTest {

	
private MockMvc mockMvc;
	
	@Mock
	private IInsegnamentoService insegnamentoServiceMock;
	
	@Mock
	private ICorsoDiStudioService corsoDiStudioServiceMock;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new InsegnamentoRestController(insegnamentoServiceMock,corsoDiStudioServiceMock,docenteServiceMock)).build();
	}
	

	/*
	@Test
	public void findInsegnamentoByIdTest() throws Exception {
		Utente utente=new Utente();
		utente.setNome("Dilan");
		utente.setCognome("DAmico");
		utente.setAbilitazione(1);
		utente.setIdUtente(1);
		utente.setEmail("dilan@gmail.com");
		utente.setPassword("dilandamico");
		Date data=new Date(1111-11-11);
		utente.setDatanascita(data);
		utente.setIndirizzo("sfdes");
		
		Docente docente=new Docente();		
		docente.setIdDocente(1);
		docente.setStipendio(1000);
		docente.setUtente(utente);
		
		Corso corso=new Corso();
		corso.setNome("Ingegneria dell'informazione");
		corso.setLivello("primo");
		corso.setIdCorso(1);
		corso.setFacolta("Ingegneria");
		corso.setDurata(3);
		corso.setAbilitazione(1);
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdInsegnamento(2);
		insegnamento.setNome("SE");
		insegnamento.setCorso(corso);
		insegnamento.setDocente(docente);
		insegnamento.setCfu(7);
		insegnamento.setAbilitazione(1);
		insegnamento.setAnno(1);
		insegnamento.setSemestre(1);
		
		when(insegnamentoServiceMock.getById(2)).thenReturn(insegnamento);
		
		mockMvc.perform(get("/insegnamento/getById/{id}", 2))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.nome", Matchers.is(insegnamento.getNome())))
		.andExpect(jsonPath("$.anno", Matchers.is(insegnamento.getAnno())))
		.andExpect(jsonPath("$.cfu", Matchers.is(insegnamento.getCfu())));

		
	    verify(insegnamentoServiceMock, times(1)).getById(2);
	    verifyNoMoreInteractions(insegnamentoServiceMock);
	}
	*/
	
	@Test
	public void getAllInsegnamentoTest() throws Exception {
		
		List<Insegnamento> insegnamenti= new ArrayList<Insegnamento>();
		List<InsegnamentoDto> insegnamentiDto = new ArrayList<InsegnamentoDto>();
		List<CorsoDiStudio> corsi = new ArrayList<CorsoDiStudio>();
		List<Docente> docenti = new ArrayList<Docente>();

		
		Utente utente=new Utente();
		utente.setNome("Luca");
		utente.setCognome("Signore");
		Date data = new Date(1991-07-31);
		utente.setDataNascita(data);
		utente.setEmail("luca@gmail.it");
		utente.setIdOrigin(0);
		utente.setIdutente(2);
		utente.setPassword("lucapass");
		
		Docente docente=new Docente();		
		docente.setIddocente(2);
		docente.setStipendio((float) 1000);
		docente.setGrado("ordinario");
		docente.setUtente(utente);
		docenti.add(docente);
		
		Docente docente2=new Docente();		
		docente2.setIddocente(3);
		docente2.setStipendio((float) 1000);
		docente2.setGrado("ordinario");
		docente2.setUtente(utente);
		docenti.add(docente2);
		
		CorsoDiStudio corso=new CorsoDiStudio();
		corso.setDipartimento("ingegneria");
		corso.setIdcorsoDiStudio(2);
		corso.setNomeCorso("ing informatica");
		corsi.add(corso);
		
		CorsoDiStudio corso2=new CorsoDiStudio();
		corso2.setDipartimento("ingegneria");
		corso2.setIdcorsoDiStudio(3);
		corso2.setNomeCorso("ing civile");
		corsi.add(corso2);
		
		Insegnamento insegnamento=new Insegnamento();
		InsegnamentoDto insegnamentoDto = new InsegnamentoDto();
		insegnamento.setIdinsegnamento(2);
		insegnamento.setNome("analisi");
		insegnamento.setAnno(1);
		insegnamento.setCfu(12);
		insegnamento.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento.setDocente(docente);
		insegnamenti.add(insegnamento);

		
		
		Insegnamento insegnamento2=new Insegnamento();
		InsegnamentoDto insegnamentoDto2 = new InsegnamentoDto();
		insegnamento2.setIdinsegnamento(3);
		insegnamento2.setNome("geometria");
		insegnamento2.setAnno(2);
		insegnamento2.setCfu(9);
		insegnamento2.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento2.setDocente(docente);
		insegnamenti.add(insegnamento2);
	
		
		when(insegnamentoServiceMock.getAll()).thenReturn(insegnamenti);
		when(docenteServiceMock.getAll()).thenReturn(docenti);
		when(corsoDiStudioServiceMock.getAll()).thenReturn(corsi);
				
		mockMvc.perform(get("/insegnamento/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].nome", Matchers.is(insegnamenti.get(0).getNome())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(insegnamenti.get(1).getNome())));

		
	    verify(insegnamentoServiceMock,times(1)).getAll();
	    verifyNoMoreInteractions(insegnamentoServiceMock);
	}
	
	@Test
	public void notFoundInsegnamentoTest() throws Exception {
		

		when(insegnamentoServiceMock.getAll()).thenThrow(new InsegnamentoNotFoundException());
		
		mockMvc.perform(get("/insegnamento/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(insegnamentoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(insegnamentoServiceMock);
	}


	
/*	
	@Test
	public void getInsegnamentoByIdCorsoTest() throws Exception {
		List<Insegnamento> insegnamenti= new ArrayList<Insegnamento>();
		Utente utente=new Utente();
		utente.setNome("Dilan");
		utente.setCognome("DAmico");
		utente.setAbilitazione(1);
		utente.setIdUtente(1);
		utente.setEmail("dilan@gmail.com");
		utente.setPassword("dilandamico");
		Date data=new Date(1111-11-11);
		utente.setDatanascita(data);
		utente.setIndirizzo("sfdes");
		
		Docente docente=new Docente();		
		docente.setIdDocente(1);
		docente.setStipendio(1000);
		docente.setUtente(utente);
		
		Corso corso=new Corso();
		corso.setNome("Ingegneria dell'informazione");
		corso.setLivello("primo");
		corso.setIdCorso(1);
		corso.setFacolta("Ingegneria");
		corso.setDurata(3);
		corso.setAbilitazione(1);
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdInsegnamento(2);
		insegnamento.setNome("SE");
		insegnamento.setCorso(corso);
		insegnamento.setDocente(docente);
		insegnamento.setCfu(7);
		insegnamento.setAbilitazione(1);
		insegnamento.setAnno(1);
		insegnamento.setSemestre(1);
		insegnamenti.add(insegnamento);
		
		Insegnamento insegnamento2=new Insegnamento();
		insegnamento2.setIdInsegnamento(3);
		insegnamento2.setNome("DB");
		insegnamento2.setCorso(corso);
		insegnamento2.setDocente(docente);
		insegnamento2.setCfu(7);
		insegnamento2.setAbilitazione(1);
		insegnamento2.setAnno(1);
		insegnamento2.setSemestre(1);
		insegnamenti.add(insegnamento2);
		
		when(insegnamentoServiceMock.getAll()).thenReturn(insegnamenti);
		
		mockMvc.perform(get("/insegnamento/getByIdCorso/{id}", 1))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].nome", Matchers.is(insegnamenti.get(0).getNome())))
		.andExpect(jsonPath("$[0].anno", Matchers.is(insegnamenti.get(0).getAnno())))
		.andExpect(jsonPath("$[0].cfu", Matchers.is(insegnamenti.get(0).getCfu())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(insegnamenti.get(1).getNome())))
		.andExpect(jsonPath("$[1].anno", Matchers.is(insegnamenti.get(1).getAnno())))
		.andExpect(jsonPath("$[1].cfu", Matchers.is(insegnamenti.get(1).getCfu())));

		
	    verify(insegnamentoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(insegnamentoServiceMock);
	}
	
	@Test
	public void getInsegnamentoByIdDocenteTest() throws Exception {
		List<Insegnamento> insegnamenti= new ArrayList<Insegnamento>();
		Utente utente=new Utente();
		utente.setNome("Dilan");
		utente.setCognome("DAmico");
		utente.setAbilitazione(1);
		utente.setIdUtente(1);
		utente.setEmail("dilan@gmail.com");
		utente.setPassword("dilandamico");
		Date data=new Date(1111-11-11);
		utente.setDatanascita(data);
		utente.setIndirizzo("sfdes");
		
		Docente docente=new Docente();		
		docente.setIdDocente(1);
		docente.setStipendio(1000);
		docente.setUtente(utente);
		
		Corso corso=new Corso();
		corso.setNome("Ingegneria dell'informazione");
		corso.setLivello("primo");
		corso.setIdCorso(1);
		corso.setFacolta("Ingegneria");
		corso.setDurata(3);
		corso.setAbilitazione(1);
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdInsegnamento(2);
		insegnamento.setNome("SE");
		insegnamento.setCorso(corso);
		insegnamento.setDocente(docente);
		insegnamento.setCfu(7);
		insegnamento.setAbilitazione(1);
		insegnamento.setAnno(1);
		insegnamento.setSemestre(1);
		insegnamenti.add(insegnamento);
		
		Insegnamento insegnamento2=new Insegnamento();
		insegnamento2.setIdInsegnamento(3);
		insegnamento2.setNome("DB");
		insegnamento2.setCorso(corso);
		insegnamento2.setDocente(docente);
		insegnamento2.setCfu(7);
		insegnamento2.setAbilitazione(1);
		insegnamento2.setAnno(1);
		insegnamento2.setSemestre(1);
		insegnamenti.add(insegnamento2);
		
		when(insegnamentoServiceMock.getAll()).thenReturn(insegnamenti);
		
		mockMvc.perform(get("/insegnamento/getByIdDocente/{id}", 1))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].nome", Matchers.is(insegnamenti.get(0).getNome())))
		.andExpect(jsonPath("$[0].anno", Matchers.is(insegnamenti.get(0).getAnno())))
		.andExpect(jsonPath("$[0].cfu", Matchers.is(insegnamenti.get(0).getCfu())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(insegnamenti.get(1).getNome())))
		.andExpect(jsonPath("$[1].anno", Matchers.is(insegnamenti.get(1).getAnno())))
		.andExpect(jsonPath("$[1].cfu", Matchers.is(insegnamenti.get(1).getCfu())));

		
	    verify(insegnamentoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(insegnamentoServiceMock);
	}
	
	@Test
	public void saveInsegnamento() throws Exception {
		Utente utente=new Utente();
		utente.setNome("Dilan");
		utente.setCognome("DAmico");
		utente.setAbilitazione(1);
		utente.setIdUtente(1);
		utente.setEmail("dilan@gmail.com");
		utente.setPassword("dilandamico");
		Date data=new Date(1111-11-11);
		utente.setDatanascita(data);
		utente.setIndirizzo("sfdes");
		
		Docente docente=new Docente();		
		docente.setIdDocente(1);
		docente.setStipendio(1000);
		docente.setUtente(utente);
		
		Corso corso=new Corso();
		corso.setNome("Ingegneria dell'informazione");
		corso.setLivello("primo");
		corso.setIdCorso(1);
		corso.setFacolta("Ingegneria");
		corso.setDurata(3);
		corso.setAbilitazione(1);
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdInsegnamento(2);
		insegnamento.setNome("SE");
		insegnamento.setCorso(corso);
		insegnamento.setDocente(docente);
		insegnamento.setCfu(7);
		insegnamento.setAbilitazione(1);
		insegnamento.setAnno(1);
		insegnamento.setSemestre(1);

		
		when(insegnamentoServiceMock.save(insegnamento)).thenReturn(insegnamento);
		
		mockMvc.perform(post("/insegnamento/save")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(toJson(insegnamento)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.nome", Matchers.is(insegnamento.getNome())));

		
	    verify(insegnamentoServiceMock, times(1)).save(insegnamento);
	    verifyNoMoreInteractions(insegnamentoServiceMock);
	}
	
	
	*/
	
}