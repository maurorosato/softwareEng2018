package it.unisalento.se.saw.rest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import it.unisalento.se.saw.Iservices.ISegreteriaService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.SegreteriaDidattica;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.SegreteriaNotFoundException;
import it.unisalento.se.saw.restapi.SegreteriaDidatticaRestController;

@RunWith(MockitoJUnitRunner.class)
public class SegreteriaRestControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private ISegreteriaService segreteriaServiceMock;
	
	@Autowired
	IUtenteService utenteService;
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new SegreteriaDidatticaRestController(segreteriaServiceMock, utenteService)).build();
	}
	
	@Test
	public void getAllSegreteriaTest() throws Exception {

		List<SegreteriaDidattica> segretari = new ArrayList<SegreteriaDidattica>();
		
		Utente utente=new Utente();
		utente.setNome("Mauro");
		utente.setCognome("Rosato");
		Date data = new Date(1992-03-16);
		utente.setDataNascita(data);
		utente.setEmail("mauro@rosato.it");
		utente.setIdOrigin(0);
		utente.setIdutente(2);
		utente.setPassword("mauropass");
		
		Utente utente2=new Utente();
		utente2.setNome("Luca");
		utente2.setCognome("Signore");
		Date data2 = new Date(1991-07-31);
		utente2.setDataNascita(data2);
		utente2.setEmail("luca@gmail.it");
		utente2.setIdOrigin(0);
		utente2.setIdutente(2);
		utente2.setPassword("lucapass");
		
		SegreteriaDidattica segreteria = new SegreteriaDidattica();
		segreteria.setIdsegreteriaDidattica(4);
		segreteria.setRuolo("sportello");
		segreteria.setStipendio(2000);
		segreteria.setUtente(utente);
		segretari.add(segreteria);
		
		SegreteriaDidattica segreteria2 = new SegreteriaDidattica();
		segreteria2.setIdsegreteriaDidattica(5);
		segreteria2.setRuolo("sportello");
		segreteria2.setStipendio(2000);
		segreteria2.setUtente(utente2);
		segretari.add(segreteria2);

		when(segreteriaServiceMock.getAll()).thenReturn(segretari);
		
		mockMvc.perform(get("/segreteria/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].ruolo", Matchers.is(segretari.get(0).getRuolo())))
		.andExpect(jsonPath("$[1].ruolo", Matchers.is(segretari.get(1).getRuolo())))
		.andExpect(jsonPath("$[0].idUserSegretario", Matchers.is(segretari.get(0).getUtente().getIdutente())))
		.andExpect(jsonPath("$[1].idUserSegretario", Matchers.is(segretari.get(1).getUtente().getIdutente())));


	    verify(segreteriaServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(segreteriaServiceMock);
	}


	
	@Test
	public void notFoundSegreteriaTest() throws Exception {
		

		when(segreteriaServiceMock.getAll()).thenThrow(new SegreteriaNotFoundException());
		
		mockMvc.perform(get("/segreteria/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(segreteriaServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(segreteriaServiceMock);
	}


}