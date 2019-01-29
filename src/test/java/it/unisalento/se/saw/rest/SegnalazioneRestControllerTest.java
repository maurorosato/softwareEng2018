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
import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.ISegnalazioneService;
import it.unisalento.se.saw.Iservices.IStrumentazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.SegnalazioneNotFoundException;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
import it.unisalento.se.saw.restapi.SegnalazioneRestController;
import it.unisalento.se.saw.restapi.StrumentazioneRestController;
	

@RunWith(MockitoJUnitRunner.class)
public class SegnalazioneRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private ISegnalazioneService segnalazioneServiceMock;
	
	@Mock
	private IAulaService aulaServiceMock;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new SegnalazioneRestController(segnalazioneServiceMock, aulaServiceMock, docenteServiceMock)).build();
	}
	
	@Test
	public void getAllSegnalazioneTest() throws Exception {
		
		List<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		List<Aula> aule = new ArrayList<Aula>();
		
		Aula aula = new Aula();
		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);
		aule.add(aula);
		
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
		
		Segnalazione segnalazione = new Segnalazione();
		segnalazione.setAula(aula);
		segnalazione.setData(data);
		segnalazione.setDescrizione("nota");
		segnalazione.setDocente(docente);
		segnalazione.setIdsegnalazione(4);
		segnalazione.setMotivazione("rotta");
		segnalazione.setOggettoInteressato("proiettore");
		segnalazione.setStatoSegnalazione("attiva");
		segnalazioni.add(segnalazione);
		
		Segnalazione segnalazione2 = new Segnalazione();
		segnalazione2.setAula(aula);
		segnalazione2.setData(data);
		segnalazione2.setDescrizione("nota");
		segnalazione2.setDocente(docente);
		segnalazione2.setIdsegnalazione(5);
		segnalazione2.setMotivazione("rotta");
		segnalazione2.setOggettoInteressato("proiettore");
		segnalazione2.setStatoSegnalazione("attiva");
		segnalazioni.add(segnalazione2);
		
		when(aulaServiceMock.getAll()).thenReturn(aule);
		when(segnalazioneServiceMock.getAll()).thenReturn(segnalazioni);
		
		mockMvc.perform(get("/segnalazione/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].descrizione", Matchers.is(segnalazioni.get(0).getDescrizione())))
		.andExpect(jsonPath("$[1].descrizione", Matchers.is(segnalazioni.get(1).getDescrizione())));

		
	    verify(segnalazioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(segnalazioneServiceMock);
	}
	
	@Test
	public void notFoundSegnalazioniTest() throws Exception {
		

		when(segnalazioneServiceMock.getAll()).thenThrow(new SegnalazioneNotFoundException());
		
		mockMvc.perform(get("/segnalazione/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(segnalazioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(segnalazioneServiceMock);
	}


	
}