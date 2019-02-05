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

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IStrumentazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
import it.unisalento.se.saw.restapi.StrumentazioneRestController;

@RunWith(MockitoJUnitRunner.class)
public class StrumentazioneRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private IStrumentazioneService strumentazioneServiceMock;
	
	@Mock
	private IAulaService aulaServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new StrumentazioneRestController(strumentazioneServiceMock, aulaServiceMock)).build();
	}
	
	@Captor
    private ArgumentCaptor<Strumentazione> savedStrumentazione;
	
	public static String toJson(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
	
	
	@Test
	public void getAllStrumentazioneTest() throws Exception {
		
		List<Strumentazione> strumentazioni = new ArrayList<Strumentazione>();
		
		Aula aula = new Aula();
		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);
		
		Strumentazione strumentazione = new Strumentazione();
		strumentazione.setAula(aula);
		strumentazione.setDescrizione("proiettore");
		strumentazione.setIdstrumentazione(2);
		strumentazione.setStato("attivo");
		strumentazioni.add(strumentazione);
		
		Strumentazione strumentazione2 = new Strumentazione();
		strumentazione2.setAula(aula);
		strumentazione2.setDescrizione("wifi");
		strumentazione2.setIdstrumentazione(3);
		strumentazione2.setStato("rotto");
		strumentazioni.add(strumentazione2);
			
		
		when(strumentazioneServiceMock.getAll()).thenReturn(strumentazioni);
		
		mockMvc.perform(get("/strumentazione/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idStrumentazione", Matchers.is(strumentazioni.get(0).getIdstrumentazione())))
		.andExpect(jsonPath("$[0].descrizione", Matchers.is(strumentazioni.get(0).getDescrizione())))
		.andExpect(jsonPath("$[0].stato", Matchers.is(strumentazioni.get(0).getStato())))
		.andExpect(jsonPath("$[0].aulaRiferimento", Matchers.is(strumentazioni.get(0).getAula().getNome())))
		.andExpect(jsonPath("$[1].idStrumentazione", Matchers.is(strumentazioni.get(1).getIdstrumentazione())))
		.andExpect(jsonPath("$[1].descrizione", Matchers.is(strumentazioni.get(1).getDescrizione())))
		.andExpect(jsonPath("$[1].stato", Matchers.is(strumentazioni.get(1).getStato())))
		.andExpect(jsonPath("$[1].aulaRiferimento", Matchers.is(strumentazioni.get(1).getAula().getNome())));
		
	    verify(strumentazioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(strumentazioneServiceMock);
	}
	
	@Test
	public void notFoundStrumentazioneTest() throws Exception {
		

		when(strumentazioneServiceMock.getAll()).thenThrow(new StrumentazioneNotFoundException());
		
		mockMvc.perform(get("/strumentazione/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(strumentazioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(strumentazioneServiceMock);
	}

	@Test
	public void saveStrumentazionTest() throws Exception {
		
		Aula aula = new Aula();
		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);
		
		Strumentazione strumentazione = new Strumentazione();
		strumentazione.setAula(aula);
		strumentazione.setDescrizione("proiettore");
		strumentazione.setIdstrumentazione(2);
		strumentazione.setStato("attivo");

		mockMvc.perform(post("/strumentazione/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(toJson(strumentazione)))
				.andExpect(status().isOk());

	    verify(strumentazioneServiceMock, times(1)).save(savedStrumentazione.capture());
	    verifyNoMoreInteractions(strumentazioneServiceMock);
		
	}

	
}