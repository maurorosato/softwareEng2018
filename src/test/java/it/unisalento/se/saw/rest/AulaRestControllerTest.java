package it.unisalento.se.saw.rest;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
	
	
//	@Test
//	public void findAulaByIdTest() throws Exception {
//		
//		Aula aula = new Aula();
//		aula.setIdaula(1);
//		aula.setNome("m1");
//		aula.setEdificio("stecca");
//		aula.setStato("disponibile");
//		aula.setCapienza(200);
//		aula.setWifi((byte) 0);
//		aula.setLatitudine(40.34339);
//		aula.setLongitudine(64.34339);
//		
//		when(aulaServiceMock.getById(1)).thenReturn(aula);
//		
//		mockMvc.perform(get("/aula/getById/{id}", 1))
//        	.andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andExpect(jsonPath("$.setIdaula", Matchers.is(aula.getIdaula())))
//			.andExpect(jsonPath("$.nome", Matchers.is(aula.getNome())))
//			.andExpect(jsonPath("$.edificio", Matchers.is(aula.getEdificio())))
//			.andExpect(jsonPath("$.stato", Matchers.is(aula.getStato())))
//			.andExpect(jsonPath("$.capienza", Matchers.is(aula.getCapienza())))
//			.andExpect(jsonPath("$.wifi", Matchers.is(aula.getWifi())))
//			.andExpect(jsonPath("$.longitudine", Matchers.is(aula.getLongitudine())))
//			.andExpect(jsonPath("$.latitudine", Matchers.is(aula.getLatitudine())));
//	
//	    verify(aulaServiceMock, times(1)).getById(1);
//	    verifyNoMoreInteractions(aulaServiceMock);
//	}
	
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
		.andExpect(jsonPath("$[0].nome", Matchers.is(aule.get(0).getNome())))
		.andExpect(jsonPath("$[1].nome", Matchers.is(aule.get(1).getNome())));

	    verify(aulaServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(aulaServiceMock);
	}

	
//	@Test
//	public void notFoundAulaTest() throws Exception {
//		
//
//		when(aulaServiceMock.getById(any(Integer.class))).thenThrow(new AulaNotFoundException());
//		
//		mockMvc.perform(get("/aula/getById/{id}", 2))
//		.andExpect(status().isBadRequest());
//		
//	    verify(aulaServiceMock, times(1)).getById(2);
//	    verifyNoMoreInteractions(aulaServiceMock);
//	}
	
	@Test
	public void notFoundAulaTest() throws Exception {
		

		when(aulaServiceMock.getAll()).thenThrow(new AulaNotFoundException());
		
		mockMvc.perform(get("/aula/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(aulaServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(aulaServiceMock);
	}


}

