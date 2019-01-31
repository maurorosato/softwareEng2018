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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IAulaService;
import it.unisalento.se.saw.Iservices.IDocenteService;
import it.unisalento.se.saw.Iservices.IEventoService;
import it.unisalento.se.saw.Iservices.IInsegnamentoService;
import it.unisalento.se.saw.Iservices.ILezioneService;
import it.unisalento.se.saw.Iservices.IPrenotazioneService;
import it.unisalento.se.saw.Iservices.IStrumentazioneService;
import it.unisalento.se.saw.converter.LezioneConverter;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Strumentazione;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.dto.LezioneDto;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.restapi.LezioneRestController;
import it.unisalento.se.saw.restapi.StrumentazioneRestController;

@RunWith(MockitoJUnitRunner.class)

public class LezioneRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private ILezioneService lezioneServiceMock;
	
	@Mock
	private IAulaService aulaServiceMock;
	
	@Mock
	private IEventoService eventoServiceMock;
	
	@Mock
	private IInsegnamentoService insegnamentoServiceMock;
	
	@Mock
	private IPrenotazioneService prenotazioneServiceMock;
	
	@Mock
	private IDocenteService docenteServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new LezioneRestController(lezioneServiceMock, aulaServiceMock, eventoServiceMock, insegnamentoServiceMock, prenotazioneServiceMock, docenteServiceMock)).build();
	}
	
//	public static String toJson(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
	
	@Test
	public void getAllLezioneTest() throws Exception {
		
		List<Lezione> lezioni = new ArrayList<Lezione>();
		List<LezioneDto> lezioniDto = new ArrayList<LezioneDto>();
		
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
		
		Insegnamento insegnamento=new Insegnamento();
		insegnamento.setIdinsegnamento(2);
		insegnamento.setNome("analisi");
		insegnamento.setAnno(1);
		insegnamento.setCfu(12);
		insegnamento.setCorsoDiStudioIdcorsoDiStudio(corso.getIdcorsoDiStudio());
		insegnamento.setDocente(docente);
		
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setDocente(docente);
		prenotazione.setIdprenotazione(2);
		Date dataInizio = new Date(2019-01-01);
		Date dataFine = new Date(2019-01-02);
		prenotazione.setDataFine(dataFine);
		prenotazione.setDataInizio(dataInizio);
		
		Evento evento = new Evento();
		evento.setAula(aula);
		evento.setDescrizione("descr");
		evento.setIdevento(2);
		evento.setInsegnamento(insegnamento);
		evento.setPrenotazione(prenotazione);
		
		Lezione lezione = new Lezione();
		lezione.setEvento(evento);
		lezione.setIdlezione(4);
		lezioni.add(lezione);
		
		Lezione lezione2 = new Lezione();
		lezione2.setEvento(evento);
		lezione2.setIdlezione(5);
		lezioni.add(lezione2);
		
		when(lezioneServiceMock.getAll()).thenReturn(lezioni);
		
		mockMvc.perform(get("/lezione/getAll"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].idLezione", Matchers.is(lezioni.get(0).getIdlezione())))
		.andExpect(jsonPath("$[1].idLezione", Matchers.is(lezioni.get(1).getIdlezione())));

		
	    verify(lezioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(lezioneServiceMock);
	}
	
	@Test
	public void notFoundLezioneTest() throws Exception {
		

		when(lezioneServiceMock.getAll()).thenThrow(new LezioneNotFoundException());
		
		mockMvc.perform(get("/lezione/getAll"))
		.andExpect(status().isBadRequest());
		
	    verify(lezioneServiceMock, times(1)).getAll();
	    verifyNoMoreInteractions(lezioneServiceMock);
	}


	
}

