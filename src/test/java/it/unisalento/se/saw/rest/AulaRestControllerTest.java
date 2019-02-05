package it.unisalento.se.saw.rest;

import static org.mockito.Mockito.*;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.restapi.AulaRestController;

@RunWith(MockitoJUnitRunner.class)
public class AulaRestControllerTest {	
	
	private MockMvc mockMvc;
	
	@Mock
	private IAulaService aulaServiceMock;
	
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new AulaRestController(aulaServiceMock)).build();
	}
	
	
	@Captor
    private ArgumentCaptor<Aula> savedAula;
	
	public static String toJson(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
	
	
	@Test
	public void getAllAulaTest() throws Exception {

		Aula aula=new Aula();
		Aula aula2=new Aula();
		List<Aula> aule = new ArrayList<Aula>();

		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);
		aule.add(aula);
		
		aula2.setIdaula(3);
		aula2.setNome("m2");
		aula2.setEdificio("stecca");
		aula2.setStato("disponibile");
		aula2.setCapienza(400);
		aula2.setWifi((byte) 1);
		aula2.setLatitudine(18.34339);
		aula2.setLongitudine(23.34339);		
		aule.add(aula2);

		when(aulaServiceMock.getAll()).thenReturn(aule);
		
		mockMvc.perform(get("/aula/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idaula", Matchers.is(aule.get(0).getIdaula())))
		.andExpect(jsonPath("$[0].edificio", Matchers.is(aule.get(0).getEdificio())))
		.andExpect(jsonPath("$[0].nome", Matchers.is(aule.get(0).getNome())))
		.andExpect(jsonPath("$[0].stato", Matchers.is(aule.get(0).getStato())))
		.andExpect(jsonPath("$[0].capienza", Matchers.is(aule.get(0).getCapienza())))
		.andExpect(jsonPath("$[0].latitudine", Matchers.is(aule.get(0).getLatitudine())))
		.andExpect(jsonPath("$[0].longitudine", Matchers.is(aule.get(0).getLongitudine())))
		.andExpect(jsonPath("$[1].idaula", Matchers.is(aule.get(1).getIdaula())))
		.andExpect(jsonPath("$[1].edificio", Matchers.is(aule.get(1).getEdificio())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(aule.get(1).getNome())))
		.andExpect(jsonPath("$[1].stato", Matchers.is(aule.get(1).getStato())))
		.andExpect(jsonPath("$[1].capienza", Matchers.is(aule.get(1).getCapienza())))
		.andExpect(jsonPath("$[1].latitudine", Matchers.is(aule.get(1).getLatitudine())))
		.andExpect(jsonPath("$[1].longitudine", Matchers.is(aule.get(1).getLongitudine())));

	    verify(aulaServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(aulaServiceMock);
	}

	@Test
	public void saveAulaTest() throws Exception {
		
		Aula aula = new Aula();
		aula.setIdaula(2);
		aula.setNome("m1");
		aula.setEdificio("stecca");
		aula.setStato("disponibile");
		aula.setCapienza(200);
		aula.setWifi((byte) 0);
		aula.setLatitudine(40.34339);
		aula.setLongitudine(64.34339);

		mockMvc.perform(post("/aula/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(toJson(aula)))
				.andExpect(status().isOk());

	    verify(aulaServiceMock, times(1)).save(savedAula.capture());
	    verifyNoMoreInteractions(aulaServiceMock);
		
	}
	
	@Test
	public void notFoundAulaTest() throws Exception {
		

		when(aulaServiceMock.getAll()).thenThrow(new AulaNotFoundException());
		
		mockMvc.perform(get("/aula/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(aulaServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(aulaServiceMock);
	}

	@Test
	public void getAuleLibereTest() throws Exception {
		
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
		
		Aula aula2 = new Aula();
		aula2.setIdaula(3);
		aula2.setNome("m2");
		aula2.setEdificio("stecca");
		aula2.setStato("disponibile");
		aula2.setCapienza(400);
		aula2.setWifi((byte) 1);
		aula2.setLatitudine(18.34339);
		aula2.setLongitudine(23.34339);		
		aule.add(aula2);
		
		Date datainizio = new Date(1992-03-16);
		Date datafine = new Date(1992-03-17);

		
		when(aulaServiceMock.getAuleLibere(datainizio, datafine)).thenReturn(aule);
		
		mockMvc.perform(get("/aula/getAuleLibere/{datainizio}/{datafine}", datainizio.toGMTString(), datafine.toGMTString()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(0)));

		
	}

}