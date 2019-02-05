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

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.restapi.CorsoDiStudioRestController;

@RunWith(MockitoJUnitRunner.class)

public class CorsoDiStudioRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private ICorsoDiStudioService corsoServiceMock;
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new CorsoDiStudioRestController(corsoServiceMock)).build();
	}
	
	@Captor
    private ArgumentCaptor<CorsoDiStudio> savedCorso;
	
	public static String toJson(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
	
	@Test
	public void getAllCorsoDiStudioTest() throws Exception {
		
		List<CorsoDiStudio> corsi = new ArrayList<CorsoDiStudio>();
		
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
		corsi.add(corso);

		CorsoDiStudio corso2=new CorsoDiStudio();
		corso2.setDipartimento("ingegneria");
		corso2.setIdcorsoDiStudio(2);
		corso2.setNomeCorso("database");
		corsi.add(corso2);
		
		
		when(corsoServiceMock.getAll()).thenReturn(corsi);
		
		mockMvc.perform(get("/corso/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idcorsoDiStudio", Matchers.is(corsi.get(0).getIdcorsoDiStudio())))
		.andExpect(jsonPath("$[1].idcorsoDiStudio", Matchers.is(corsi.get(1).getIdcorsoDiStudio())))
		.andExpect(jsonPath("$[0].dipartimento", Matchers.is(corsi.get(0).getDipartimento())))
		.andExpect(jsonPath("$[1].dipartimento", Matchers.is(corsi.get(1).getDipartimento())))
		.andExpect(jsonPath("$[0].nomeCorso", Matchers.is(corsi.get(0).getNomeCorso())))
		.andExpect(jsonPath("$[1].nomeCorso", Matchers.is(corsi.get(1).getNomeCorso())));

		
	    verify(corsoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(corsoServiceMock);
	}
	
	@Test
	public void notFoundCorsoDiStudioTest() throws Exception {
		
		when(corsoServiceMock.getAll()).thenThrow(new CorsoDiStudioNotFoundException());
		
		mockMvc.perform(get("/corso/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(corsoServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(corsoServiceMock);
	}


	@Test
	public void saveCorsoTest() throws Exception {
		
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

		mockMvc.perform(post("/corso/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(toJson(corso)))
				.andExpect(status().isOk());

	    verify(corsoServiceMock, times(1)).save(savedCorso.capture());
	    verifyNoMoreInteractions(corsoServiceMock);
		
	}
	
	@Test
	public void getByIdCorsoTest() throws Exception {	
		
		List<CorsoDiStudio> corsi = new ArrayList<CorsoDiStudio>();
		
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
		corsi.add(corso);

		CorsoDiStudio corso2=new CorsoDiStudio();
		corso2.setDipartimento("ingegneria");
		corso2.setIdcorsoDiStudio(2);
		corso2.setNomeCorso("database");
		corsi.add(corso2);
		
		when(corsoServiceMock.getById(2)).thenReturn(corso);
		
		mockMvc.perform(get("/corso/getById/{id}",2))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(3)))
		.andExpect(jsonPath("$.idcorsoDiStudio", Matchers.is(corsi.get(0).getIdcorsoDiStudio())))
		.andExpect(jsonPath("$.dipartimento", Matchers.is(corsi.get(0).getDipartimento())))
		.andExpect(jsonPath("$.nomeCorso", Matchers.is(corsi.get(0).getNomeCorso())));
		
	    verify(corsoServiceMock, times(1)).getById(2);
	    verifyNoMoreInteractions(corsoServiceMock);

	}

	
	
}

