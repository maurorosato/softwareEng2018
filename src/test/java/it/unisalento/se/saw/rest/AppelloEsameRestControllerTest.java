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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IAppelloEsameService;
import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.restapi.AppelloEsameRestController;
	

@RunWith(MockitoJUnitRunner.class)
public class AppelloEsameRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private IAppelloEsameService esameServiceMock;
	
	@Mock
	private IAulaService aulaServiceMock;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	@Mock
	private IEventoService eventoServiceMock;
	
	@Mock
	private IInsegnamentoService insegnamentoServiceMock;
	
	@Mock
	private IPrenotazioneService prenotazioneServiceMock;
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new AppelloEsameRestController(esameServiceMock, aulaServiceMock, eventoServiceMock, insegnamentoServiceMock, prenotazioneServiceMock, docenteServiceMock)).build();
	}
	

	@Test
	public void getAllAppelloEsameTest() throws Exception {
		
		List<AppelloEsame> esami = new ArrayList<AppelloEsame>();
		
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
		
		AppelloEsame esame = new AppelloEsame();
		esame.setEvento(evento);
		esame.setTipologia("scritto");
		esame.setIdappelloEsame(2);
		esami.add(esame);
		
		AppelloEsame esame2 = new AppelloEsame();
		esame2.setEvento(evento);
		esame2.setTipologia("scritto");
		esame2.setIdappelloEsame(3);
		esami.add(esame2);
		
		
		
		when(esameServiceMock.getAll()).thenReturn(esami);
		
		mockMvc.perform(get("/appelloEsame/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idAppelloEsame", Matchers.is(esami.get(0).getIdappelloEsame())))
		.andExpect(jsonPath("$[1].idAppelloEsame", Matchers.is(esami.get(1).getIdappelloEsame())));

		
	    verify(esameServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(esameServiceMock);
	}
	
	@Test
	public void notFoundEsameTest() throws Exception {
		

		when(esameServiceMock.getAll()).thenThrow(new AppelloEsameNotFoundException());
		
		mockMvc.perform(get("/appelloEsame/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(esameServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(esameServiceMock);
	}


	
}