package it.unisalento.se.saw.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.Iservices.IMaterialeDidatticoService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.MaterialeDidatticoNotFoundException;
import it.unisalento.se.saw.restapi.MaterialeDidatticoRestController;

@RunWith(MockitoJUnitRunner.class)
public class MaterialeDidatticoRestControllerTest {

private MockMvc mockMvc;
	
	@Mock
	private IMaterialeDidatticoService materialeServiceMock;
	
	@Mock
	private IEventoService eventoServiceMock;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	@Mock
	private ILezioneService lezioneServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new MaterialeDidatticoRestController(materialeServiceMock, eventoServiceMock, lezioneServiceMock, docenteServiceMock)).build();
	}
	
	
	@Captor
    private ArgumentCaptor<MaterialeDidattico> savedMateriale;
	
	public static String toJson(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
	
	@Test
	public void getAllMaterialeTest() throws Exception {
		
		List<MaterialeDidattico> materiali = new ArrayList<MaterialeDidattico>();

		Aula aula = new Aula();
		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);
		
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
		
		CorsoDiStudio corso=new CorsoDiStudio();
		corso.setDipartimento("ingegneria");
		corso.setIdcorsoDiStudio(2);
		corso.setNomeCorso("software");
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdinsegnamento(2);
		insegnamento.setNome("analisi");
		insegnamento.setAnno(1);
		insegnamento.setCfu(12);
		insegnamento.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento.setDocente(docente);
		
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setDocente(docente);
		prenotazione.setIdprenotazione(2);
		Date dataInizio = new Date(2019-01-01);
		Date dataFine = new Date(2019-01-02);
		prenotazione.setDataFine(dataFine);
		prenotazione.setDataInizio(dataInizio);
		
		Evento evento = new Evento();
		evento.setAula(aula);
		evento.setDescrizione("descr");
		evento.setIdevento(2);
		evento.setInsegnamento(insegnamento);
		evento.setPrenotazione(prenotazione);
		
		Lezione lezione = new Lezione();
		lezione.setEvento(evento);
		lezione.setIdlezione(4);
		
		MaterialeDidattico materiale = new MaterialeDidattico();
		materiale.setDocente(docente);
		materiale.setIdmaterialeDidattico(4);
		materiale.setLezione(lezione);
		materiale.setLink("www.google.com");
		materiale.setNome("materiale");
		materiali.add(materiale);
		
		MaterialeDidattico materiale2 = new MaterialeDidattico();
		materiale2.setDocente(docente);
		materiale2.setIdmaterialeDidattico(5);
		materiale2.setLezione(lezione);
		materiale2.setLink("www.google.com");
		materiale2.setNome("materiale");
		materiali.add(materiale2);
		
		when(materialeServiceMock.getAll()).thenReturn(materiali);
		
		mockMvc.perform(get("/materialeDidattico/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idMaterialeDidattico", Matchers.is(materiali.get(0).getIdmaterialeDidattico())))
		.andExpect(jsonPath("$[0].nome", Matchers.is(materiali.get(0).getNome())))
		.andExpect(jsonPath("$[0].link", Matchers.is(materiali.get(0).getLink())))
		.andExpect(jsonPath("$[0].idEvento", Matchers.is(materiali.get(0).getLezione().getEvento().getIdevento())))
		.andExpect(jsonPath("$[0].idUtente", Matchers.is(materiali.get(0).getDocente().getUtente().getIdutente())))
		.andExpect(jsonPath("$[0].idDocente", Matchers.is(materiali.get(0).getDocente().getIddocente())))
		.andExpect(jsonPath("$[1].idMaterialeDidattico", Matchers.is(materiali.get(1).getIdmaterialeDidattico())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(materiali.get(1).getNome())))
		.andExpect(jsonPath("$[1].link", Matchers.is(materiali.get(1).getLink())))
		.andExpect(jsonPath("$[1].idEvento", Matchers.is(materiali.get(1).getLezione().getEvento().getIdevento())))
		.andExpect(jsonPath("$[1].idUtente", Matchers.is(materiali.get(1).getDocente().getUtente().getIdutente())))
		.andExpect(jsonPath("$[1].idDocente", Matchers.is(materiali.get(1).getDocente().getIddocente())));
		
	    verify(materialeServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(materialeServiceMock);

	}
	
	@Test
	public void notFoundMaterialeTest() throws Exception {		

		when(materialeServiceMock.getAll()).thenThrow(new MaterialeDidatticoNotFoundException());
		
		mockMvc.perform(get("/materialeDidattico/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(materialeServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(materialeServiceMock);
	}
	
	@Test
	public void saveMaterialeTest() throws Exception {
		List<MaterialeDidattico> materiali = new ArrayList<MaterialeDidattico>();

		Aula aula = new Aula();
		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);
		
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
		
		CorsoDiStudio corso=new CorsoDiStudio();
		corso.setDipartimento("ingegneria");
		corso.setIdcorsoDiStudio(2);
		corso.setNomeCorso("software");
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdinsegnamento(2);
		insegnamento.setNome("analisi");
		insegnamento.setAnno(1);
		insegnamento.setCfu(12);
		insegnamento.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento.setDocente(docente);
		
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setDocente(docente);
		prenotazione.setIdprenotazione(2);
		Date dataInizio = new Date(2019-01-01);
		Date dataFine = new Date(2019-01-02);
		prenotazione.setDataFine(dataFine);
		prenotazione.setDataInizio(dataInizio);
		
		Evento evento = new Evento();
		evento.setAula(aula);
		evento.setDescrizione("descr");
		evento.setIdevento(2);
		evento.setInsegnamento(insegnamento);
		evento.setPrenotazione(prenotazione);
		
		Lezione lezione = new Lezione();
		lezione.setEvento(evento);
		lezione.setIdlezione(4);
		
		MaterialeDidattico materiale = new MaterialeDidattico();
		materiale.setDocente(docente);
		materiale.setIdmaterialeDidattico(4);
		materiale.setLezione(lezione);
		materiale.setLink("www.google.com");
		materiale.setNome("materiale");


		mockMvc.perform(post("/materialeDidattico/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(toJson(materiale)))
				.andExpect(status().isOk());

	    verify(materialeServiceMock, times(1)).save(savedMateriale.capture());
	    verifyNoMoreInteractions(materialeServiceMock);
		
	}
	



}
		
		
