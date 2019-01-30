package it.unisalento.se.saw.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import it.unisalento.se.saw.Iservices.IIscrizioneService;
import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Iscrizione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.exceptions.IscrizioneNotFoundException;
import it.unisalento.se.saw.restapi.IscrizioneRestController;
	

@RunWith(MockitoJUnitRunner.class)
public class IscrizioneRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private IIscrizioneService iscrizioneServiceMock;
	
	@Mock
	private IStudenteService studenteServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new IscrizioneRestController(iscrizioneServiceMock, studenteServiceMock)).build();
	}
	
	@Test
	public void getAllIscrizioneStudenteTest() throws Exception {
		
		List<Iscrizione> iscrizioni = new ArrayList<Iscrizione>();
		List<Studente> studenti = new ArrayList<Studente>();
		
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
		
		Utente utente2=new Utente();
		utente2.setNome("Mauro");
		utente2.setCognome("Rosato");
		Date data2 = new Date(1992-03-16);
		utente2.setDataNascita(data2);
		utente2.setEmail("mauro@rosato.it");
		utente2.setIdOrigin(0);
		utente2.setIdutente(3);
		utente2.setPassword("mauropass");
		
		Studente studente = new Studente();
		studente.setCodiceFiscale("mrorst31l91e506f");
		studente.setCorsoDiStudioIdcorsoDiStudio(3);
		studente.setIdstudente(5);
		studente.setIndirizzo("via del ciclamino");
		studente.setMatricola("10087654");
		studente.setNazione("ITA");
		studente.setUtente(utente);
		studenti.add(studente);
		
		Docente docente=new Docente();		
		docente.setIddocente(2);
		docente.setStipendio((float) 1000);
		docente.setGrado("ordinario");
		docente.setUtente(utente2);
		
		CorsoDiStudio corso=new CorsoDiStudio();
		corso.setDipartimento("ingegneria");
		corso.setIdcorsoDiStudio(2);
		corso.setNomeCorso("software");
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdinsegnamento(2);
		insegnamento.setNome("analisi");
		insegnamento.setAnno(1);
		insegnamento.setCfu(12);
		insegnamento.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento.setDocente(docente);
		
		Insegnamento insegnamento2=new Insegnamento();
		insegnamento2.setIdinsegnamento(3);
		insegnamento2.setNome("geometria");
		insegnamento2.setAnno(2);
		insegnamento2.setCfu(9);
		insegnamento2.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento2.setDocente(docente);
		
		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdiscrizione(3);
		iscrizione.setInsegnamento(insegnamento);
		iscrizione.setStato("attivo");
		iscrizione.setStudente(studente);
		iscrizioni.add(iscrizione);
		
		Iscrizione iscrizione2 = new Iscrizione();
		iscrizione2.setIdiscrizione(3);
		iscrizione2.setInsegnamento(insegnamento2);
		iscrizione2.setStato("attivo");
		iscrizione2.setStudente(studente);
		iscrizioni.add(iscrizione2);
		
		when(studenteServiceMock.getAll()).thenReturn(studenti);
		when(iscrizioneServiceMock.getIscrizione(insegnamento, studente)).thenReturn(iscrizioni);
		
		mockMvc.perform(get("/iscrizione/getIscrizione/{idInsegnamento}/{idUserStudente}", insegnamento.getIdinsegnamento(), studente.getIdstudente()))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(0)));

	}
	
	@Test
	public void notFoundIscrizioneTest() throws Exception {
		
		List<Iscrizione> iscrizioni = new ArrayList<Iscrizione>();
		List<Studente> studenti = new ArrayList<Studente>();
		
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
		
		Utente utente2=new Utente();
		utente2.setNome("Mauro");
		utente2.setCognome("Rosato");
		Date data2 = new Date(1992-03-16);
		utente2.setDataNascita(data2);
		utente2.setEmail("mauro@rosato.it");
		utente2.setIdOrigin(0);
		utente2.setIdutente(2);
		utente2.setPassword("mauropass");
		
		Studente studente = new Studente();
		studente.setCodiceFiscale("mrorst31l91e506f");
		studente.setCorsoDiStudioIdcorsoDiStudio(3);
		studente.setIdstudente(5);
		studente.setIndirizzo("via del ciclamino");
		studente.setMatricola("10087654");
		studente.setNazione("ITA");
		studente.setUtente(utente2);
		studenti.add(studente);
		
		Docente docente=new Docente();		
		docente.setIddocente(2);
		docente.setStipendio((float) 1000);
		docente.setGrado("ordinario");
		docente.setUtente(utente);
		
		CorsoDiStudio corso=new CorsoDiStudio();
		corso.setDipartimento("ingegneria");
		corso.setIdcorsoDiStudio(2);
		corso.setNomeCorso("software");
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdinsegnamento(2);
		insegnamento.setNome("analisi");
		insegnamento.setAnno(1);
		insegnamento.setCfu(12);
		insegnamento.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento.setDocente(docente);
		
		Insegnamento insegnamento2=new Insegnamento();
		insegnamento2.setIdinsegnamento(3);
		insegnamento2.setNome("geometria");
		insegnamento2.setAnno(2);
		insegnamento2.setCfu(9);
		insegnamento2.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento2.setDocente(docente);
		
		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdiscrizione(3);
		iscrizione.setInsegnamento(insegnamento);
		iscrizione.setStato("attivo");
		iscrizione.setStudente(studente);
		iscrizioni.add(iscrizione);
		
		Iscrizione iscrizione2 = new Iscrizione();
		iscrizione2.setIdiscrizione(3);
		iscrizione2.setInsegnamento(insegnamento2);
		iscrizione2.setStato("attivo");
		iscrizione2.setStudente(studente);
		iscrizioni.add(iscrizione2);

		when(iscrizioneServiceMock.getIscrizione(insegnamento, studente)).thenThrow(new IscrizioneNotFoundException());
		
		mockMvc.perform(get("/iscrizione/getIscrizione/{idInsegnamento}/{idUserStudente}", insegnamento.getIdinsegnamento(), studente.getIdstudente()))
		.andExpect(status().is2xxSuccessful());


	}


	
}