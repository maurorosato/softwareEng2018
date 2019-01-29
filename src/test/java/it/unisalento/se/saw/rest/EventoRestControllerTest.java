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

import it.unisalento.se.saw.Iservices.IAppelloEsameService;
import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.EventoNotFoundException;
import it.unisalento.se.saw.restapi.EventoRestController;
	

@RunWith(MockitoJUnitRunner.class)
public class EventoRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private IAppelloEsameService esameServiceMock;
	
	@Mock
	private IEventoService eventoServiceMock;

	@Mock
	private ICorsoDiStudioService corsoDiStudioServiceMock;
	
	@Mock
	private ILezioneService lezioneServiceMock;
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new EventoRestController(eventoServiceMock, esameServiceMock, corsoDiStudioServiceMock, lezioneServiceMock)).build();
	}
	
	@Test
	public void getAllEventoTest() throws Exception {
		
		List<Evento> eventi = new ArrayList<Evento>();
		
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
		eventi.add(evento);
		
		Evento evento2 = new Evento();
		evento2.setAula(aula);
		evento2.setDescrizione("descr");
		evento2.setIdevento(3);
		evento2.setInsegnamento(insegnamento);
		evento2.setPrenotazione(prenotazione);
		eventi.add(evento2);
	
		when(eventoServiceMock.getAll()).thenReturn(eventi);
		
		mockMvc.perform(get("/evento/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].aula", Matchers.is(eventi.get(0).getAula().getNome())))
		.andExpect(jsonPath("$[1].aula", Matchers.is(eventi.get(1).getAula().getNome())));

		
	    verify(eventoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(eventoServiceMock);
	}
	
	@Test
	public void notFoundEventoTest() throws Exception {
		

		when(eventoServiceMock.getAll()).thenThrow(new EventoNotFoundException());
		
		mockMvc.perform(get("/evento/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(eventoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(eventoServiceMock);
	}


	
}