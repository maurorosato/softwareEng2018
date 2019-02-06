package it.unisalento.se.saw.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
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
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.InsegnamentoDto;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.restapi.DocenteRestController;

@RunWith(MockitoJUnitRunner.class)
public class DocenteRestControllerTest {

private MockMvc mockMvc;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	@Mock
	private IUtenteService utenteServiceMock;
	
	@Mock
	private INumeroTelefonoService numeroServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new DocenteRestController(docenteServiceMock, utenteServiceMock, numeroServiceMock)).build();
	}
	
	@Captor
    private ArgumentCaptor<Docente> savedDocente;
	
	public static String toJson(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
	
	@Test
	public void getAllDocentiTest() throws Exception {
		
		List<Docente> docenti = new ArrayList<Docente>();

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
		
		NumeroTelefono num = new NumeroTelefono();
		num.setNumeroTelefono("54");
		num.setUtente(utente);
		
		NumeroTelefono num2 = new NumeroTelefono();
		num2.setNumeroTelefono("45");
		num2.setUtente(utente2);
		
		Docente docente = new Docente();
		docente.setGrado("ordinario");
		docente.setIddocente(3);
		docente.setStipendio((float) 1000.2);
		docente.setUtente(utente);
		docenti.add(docente);
		
		Docente docente2 = new Docente();
		docente2.setGrado("ordinario");
		docente2.setIddocente(4);
		docente2.setStipendio((float) 1000.2);
		docente2.setUtente(utente2);
		docenti.add(docente2);
		
		
		when(docenteServiceMock.getAll()).thenReturn(docenti);
		
		mockMvc.perform(get("/docente/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idDocente", Matchers.is(docenti.get(0).getIddocente())))
		.andExpect(jsonPath("$[0].nome", Matchers.is(docenti.get(0).getUtente().getNome())))
		.andExpect(jsonPath("$[0].cognome", Matchers.is(docenti.get(0).getUtente().getCognome())))
		.andExpect(jsonPath("$[0].email", Matchers.is(docenti.get(0).getUtente().getEmail())))
		.andExpect(jsonPath("$[0].grado", Matchers.is(docenti.get(0).getGrado())))
		.andExpect(jsonPath("$[0].password", Matchers.is(docenti.get(0).getUtente().getPassword())))
		.andExpect(jsonPath("$[0].idUserDocente", Matchers.is(docenti.get(0).getUtente().getIdutente())))
		.andExpect(jsonPath("$[1].idDocente", Matchers.is(docenti.get(1).getIddocente())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(docenti.get(1).getUtente().getNome())))
		.andExpect(jsonPath("$[1].cognome", Matchers.is(docenti.get(1).getUtente().getCognome())))
		.andExpect(jsonPath("$[1].email", Matchers.is(docenti.get(1).getUtente().getEmail())))
		.andExpect(jsonPath("$[1].grado", Matchers.is(docenti.get(1).getGrado())))
		.andExpect(jsonPath("$[1].password", Matchers.is(docenti.get(1).getUtente().getPassword())))
		.andExpect(jsonPath("$[1].idUserDocente", Matchers.is(docenti.get(1).getUtente().getIdutente())));

		
	    verify(docenteServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(docenteServiceMock);

	}
	
	@Test
	public void getByIdDocenteTest() throws Exception {
		
		List<Docente> docenti = new ArrayList<Docente>();
		List<Utente> utenti = new ArrayList<Utente>();
		
		Utente utente=new Utente();
		utente.setNome("Luca");
		utente.setCognome("Signore");
		Date data = new Date(1991-07-31);
		utente.setDataNascita(data);
		utente.setEmail("luca@gmail.it");
		utente.setIdOrigin(0);
		utente.setIdutente(2);
		utente.setPassword("lucapass");
		utenti.add(utente);
		
		Docente docente=new Docente();		
		docente.setIddocente(8);
		docente.setStipendio((float) 1000);
		docente.setGrado("ordinario");
		docente.setUtente(utente);
		docenti.add(docente);
	
		when(utenteServiceMock.getAll()).thenReturn(utenti);
		when(utenteServiceMock.getById(2)).thenReturn(utente);
		when(docenteServiceMock.getById(8)).thenReturn(docente);
				
		mockMvc.perform(get("/docente/getById/{id}",8))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(11)))
		.andExpect(jsonPath("$.nome", Matchers.is(docente.getUtente().getNome())))
		.andExpect(jsonPath("$.cognome", Matchers.is(docente.getUtente().getCognome())));
		
	    verify(docenteServiceMock,times(1)).getById(8);
	    verifyNoMoreInteractions(docenteServiceMock);
	}
	
	
	@Test
	public void notFoundDocenteTest() throws Exception {
		

		when(docenteServiceMock.getAll()).thenThrow(new DocenteNotFoundException());
		
		mockMvc.perform(get("/docente/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(docenteServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(docenteServiceMock);
	}

	@Test
	public void saveDocenteTest() throws Exception {
		
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
		
		NumeroTelefono num = new NumeroTelefono();
		num.setNumeroTelefono("54");
		num.setUtente(utente);
		
		NumeroTelefono num2 = new NumeroTelefono();
		num2.setNumeroTelefono("45");
		num2.setUtente(utente2);
		
		Docente docente = new Docente();
		docente.setGrado("ordinario");
		docente.setIddocente(3);
		docente.setStipendio((float) 1000.2);
		docente.setUtente(utente);


		mockMvc.perform(post("/docente/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(toJson(docente)))
				.andExpect(status().isOk());

	    verify(docenteServiceMock, times(1)).save(savedDocente.capture());
	    verifyNoMoreInteractions(docenteServiceMock);
		
	}
	
	@Test
	public void getByIdSecondTest() throws Exception {
		
		List<Docente> docenti = new ArrayList<Docente>();

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
		
		NumeroTelefono num = new NumeroTelefono();
		num.setNumeroTelefono("54");
		num.setUtente(utente);
		
		NumeroTelefono num2 = new NumeroTelefono();
		num2.setNumeroTelefono("45");
		num2.setUtente(utente2);
		
		Docente docente = new Docente();
		docente.setGrado("ordinario");
		docente.setIddocente(3);
		docente.setStipendio((float) 1000.2);
		docente.setUtente(utente);
		docenti.add(docente);
		
		Docente docente2 = new Docente();
		docente2.setGrado("ordinario");
		docente2.setIddocente(4);
		docente2.setStipendio((float) 1000.2);
		docente2.setUtente(utente2);
		docenti.add(docente2);

		when(docenteServiceMock.getById(2)).thenThrow(new DocenteNotFoundException());
		
		mockMvc.perform(get("/docente/getById/{id}",2))
		.andExpect(status().isBadRequest());
		
	    verify(docenteServiceMock, times(1)).getById(2);
	    verifyNoMoreInteractions(docenteServiceMock);
	}


}
		
		
