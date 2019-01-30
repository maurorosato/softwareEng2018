package it.unisalento.se.saw.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
//import static org.mockito.Matchers.any;
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

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;
import it.unisalento.se.saw.restapi.UtenteRestController;

@RunWith(MockitoJUnitRunner.class)
public class UtenteRestControllerTest {

private MockMvc mockMvc;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	@Mock
	private IUtenteService utenteServiceMock;
	
	@Mock
	private IStudenteService studenteServiceMock;
	
	@Mock
	private ICorsoDiStudioService corsoServiceMock;
	
	@Mock
	private INumeroTelefonoService numeroServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new UtenteRestController(utenteServiceMock, docenteServiceMock, studenteServiceMock, corsoServiceMock, numeroServiceMock)).build();
	}
	
	@Test
	public void getAllUtenteTest() throws Exception {
		
		List<Utente> utenti = new ArrayList<Utente>();

		Utente utente=new Utente();
		utente.setNome("Mauro");
		utente.setCognome("Rosato");
		Date data = new Date(1992-03-16);
		utente.setDataNascita(data);
		utente.setEmail("mauro@rosato.it");
		utente.setIdOrigin(0);
		utente.setIdutente(2);
		utente.setPassword("mauropass");
		utenti.add(utente);
		
		Utente utente2=new Utente();
		utente2.setNome("Luca");
		utente2.setCognome("Signore");
		Date data2 = new Date(1991-07-31);
		utente2.setDataNascita(data2);
		utente2.setEmail("luca@gmail.it");
		utente2.setIdOrigin(0);
		utente2.setIdutente(2);
		utente2.setPassword("lucapass");
		utenti.add(utente2);
		
		
		when(utenteServiceMock.getAll()).thenReturn(utenti);
		
		mockMvc.perform(get("/utente/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].nome", Matchers.is(utenti.get(0).getNome())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(utenti.get(1).getNome())));

		
	    verify(utenteServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(utenteServiceMock);

	}
	
	
	@Test
	public void getByIdUtenteTest() throws Exception {
		
		Utente utente=new Utente();
		utente.setNome("Mauro");
		utente.setCognome("Rosato");
		Date data = new Date(1992-03-16);
		utente.setDataNascita(data);
		utente.setEmail("mauro@rosato.it");
		utente.setIdOrigin(0);
		utente.setIdutente(6);
		utente.setPassword("mauropass");

		when(utenteServiceMock.getById(6)).thenReturn(utente);
		
		mockMvc.perform(get("/utente/getById/{id}", utente.getIdutente()))
		.andExpect(status().isOk());
		
	    verify(utenteServiceMock, times(1)).getById(6);
	    verifyNoMoreInteractions(utenteServiceMock);
	}
	
	@Test
	public void notFoundUtenteTest() throws Exception {
		

		when(utenteServiceMock.getAll()).thenThrow(new UtenteNotFoundException());
		
		mockMvc.perform(get("/utente/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(utenteServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(utenteServiceMock);
	}



}
		
		
