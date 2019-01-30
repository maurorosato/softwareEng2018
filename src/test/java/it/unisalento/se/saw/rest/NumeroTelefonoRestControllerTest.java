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

import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.NumeroTelefonoNotFoundException;
import it.unisalento.se.saw.restapi.NumeroTelefonoRestController;

@RunWith(MockitoJUnitRunner.class)
public class NumeroTelefonoRestControllerTest {

private MockMvc mockMvc;
	
	@Mock
	private INumeroTelefonoService numeroServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new NumeroTelefonoRestController(numeroServiceMock)).build();
	}
	
	@Test
	public void getAllNumeroTelefonoTest() throws Exception {
		
		List<NumeroTelefono> numeri = new ArrayList<NumeroTelefono>();

		Utente utente=new Utente();
		utente.setNome("Mauro");
		utente.setCognome("Rosato");
		Date data = new Date(1992-03-16);
		utente.setDataNascita(data);
		utente.setEmail("mauro@rosato.it");
		utente.setIdOrigin(0);
		utente.setIdutente(2);
		utente.setPassword("mauropass");
		
		NumeroTelefono numero = new NumeroTelefono();
		numero.setNumeroTelefono("3804372964");
		numero.setUtente(utente);
		numeri.add(numero);

		NumeroTelefono numero2 = new NumeroTelefono();
		numero2.setNumeroTelefono("3338790838");
		numero2.setUtente(utente);
		numeri.add(numero2);
		
		when(numeroServiceMock.getAll()).thenReturn(numeri);
		
		mockMvc.perform(get("/numeroTelefono/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].numeroTelefono", Matchers.is(numeri.get(0).getNumeroTelefono())))
		.andExpect(jsonPath("$[1].numeroTelefono", Matchers.is(numeri.get(1).getNumeroTelefono())));

		
	    verify(numeroServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(numeroServiceMock);

	}
	
	@Test
	public void notFoundNumeroTest() throws Exception {
		

		when(numeroServiceMock.getAll()).thenThrow(new NumeroTelefonoNotFoundException());
		
		mockMvc.perform(get("/numeroTelefono/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(numeroServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(numeroServiceMock);
	}



}
		
		
