package it.unisalento.se.saw.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.Iservices.INumeroTelefonoService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IUtenteService;
import it.unisalento.se.saw.converter.StudenteConverter;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.NumeroTelefono;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.StudenteDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.StrumentazioneNotFoundException;
import it.unisalento.se.saw.exceptions.StudenteNotFoundException;
import it.unisalento.se.saw.exceptions.UtenteNotFoundException;
import it.unisalento.se.saw.restapi.StudenteRestController;

@RunWith(MockitoJUnitRunner.class)
public class StudenteRestControllerTest {

private MockMvc mockMvc;
	
	@Mock
	private IStudenteService studenteServiceMock;
	
	@Mock
	private IUtenteService utenteServiceMock;
	
	@Mock
	private ICorsoDiStudioService corsoServiceMock;
	
	@Mock
	private INumeroTelefonoService numeroServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new StudenteRestController(studenteServiceMock, utenteServiceMock, corsoServiceMock, numeroServiceMock)).build();
	}
	
//	public static String toJson(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
	
	@Test
	public void getAllStudenteTest() throws Exception {
		
		List<Studente> studenti = new ArrayList<Studente>();
		List<StudenteDto> studentiDto = new ArrayList<StudenteDto>();
		List<CorsoDiStudio> corsi = new ArrayList<CorsoDiStudio>();
		List<NumeroTelefono> numeri = new ArrayList<NumeroTelefono>();

		CorsoDiStudio corso=new CorsoDiStudio();
		corso.setDipartimento("ingegneria");
		corso.setIdcorsoDiStudio(2);
		corso.setNomeCorso("ing informatica");
		corsi.add(corso);
		
		CorsoDiStudio corso2=new CorsoDiStudio();
		corso2.setDipartimento("ingegneria");
		corso2.setIdcorsoDiStudio(3);
		corso2.setNomeCorso("ing civile");
		corsi.add(corso2);

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
		numeri.add(num);
		
		NumeroTelefono num2 = new NumeroTelefono();
		num2.setNumeroTelefono("45");
		num2.setUtente(utente2);
		numeri.add(num2);
		
		Studente studente = new Studente();
		StudenteDto studenteDto = new StudenteDto();
		studente.setCodiceFiscale("mrorst31l91e506f");
		studente.setCorsoDiStudioIdcorsoDiStudio(3);
		studente.setIdstudente(5);
		studente.setIndirizzo("via del ciclamino");
		studente.setMatricola("10087654");
		studente.setNazione("ITA");
		studente.setUtente(utente);
		studenti.add(studente);
		studenteDto = StudenteConverter.domainToDto(studente, corsi, numeri);
		studentiDto.add(studenteDto);
		
		Studente studente2 = new Studente();
		StudenteDto studenteDto2 = new StudenteDto();
		studente2.setCodiceFiscale("sgnlcu31l91e506f");
		studente2.setCorsoDiStudioIdcorsoDiStudio(3);
		studente2.setIdstudente(6);
		studente2.setIndirizzo("via cavour");
		studente2.setMatricola("34567876");
		studente2.setNazione("ITA");
		studente2.setUtente(utente2);
		studenti.add(studente2);
		studenteDto2 = StudenteConverter.domainToDto(studente2, corsi, numeri);
		studentiDto.add(studenteDto2);
		
		when(studenteServiceMock.getAll()).thenReturn(studenti);
		
		mockMvc.perform(get("/studente/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idStudente", Matchers.is(studenti.get(0).getIdstudente())))
		.andExpect(jsonPath("$[1].idStudente", Matchers.is(studenti.get(1).getIdstudente())));

		
	    verify(studenteServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(studenteServiceMock);

	}
	
	@Test
	public void notFoundStudenteTest() throws Exception {
		

		when(studenteServiceMock.getAll()).thenThrow(new StudenteNotFoundException());
		
		mockMvc.perform(get("/studente/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(studenteServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(studenteServiceMock);
	}



}
		
		
