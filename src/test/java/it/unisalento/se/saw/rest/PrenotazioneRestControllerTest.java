package it.unisalento.se.saw.rest;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
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

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.PrenotazioneNotFoundException;
import it.unisalento.se.saw.restapi.AulaRestController;
import it.unisalento.se.saw.restapi.PrenotazioneRestController;

@RunWith(MockitoJUnitRunner.class)
public class PrenotazioneRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private IPrenotazioneService prenotazioneServiceMock;
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new PrenotazioneRestController(prenotazioneServiceMock)).build();
	}
	
	
	@Test
	public void getAllPrenotazioneTest() throws Exception {

		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();

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
		prenotazioni.add(prenotazione);
		
		Prenotazione prenotazione2 = new Prenotazione();
		prenotazione2.setDocente(docente);
		prenotazione2.setIdprenotazione(2);
		prenotazione.setDataFine(dataFine);
		prenotazione.setDataInizio(dataInizio);
		prenotazioni.add(prenotazione2);
		

		when(prenotazioneServiceMock.getAll()).thenReturn(prenotazioni);
		
		mockMvc.perform(get("/prenotazione/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idDocente", Matchers.is(prenotazioni.get(0).getDocente().getIddocente())))
		.andExpect(jsonPath("$[1].idDocente", Matchers.is(prenotazioni.get(1).getDocente().getIddocente())));

	    verify(prenotazioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(prenotazioneServiceMock);
	}

	
	@Test
	public void notFoundPrenotazioneTest() throws Exception {
		

		when(prenotazioneServiceMock.getAll()).thenThrow(new PrenotazioneNotFoundException());
		
		mockMvc.perform(get("/prenotazione/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(prenotazioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(prenotazioneServiceMock);
	}

	
}

