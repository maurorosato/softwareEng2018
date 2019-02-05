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

import it.unisalento.se.saw.Iservices.IStudenteService;
import it.unisalento.se.saw.Iservices.IValutazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Evento;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.domain.Prenotazione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Utente;
import it.unisalento.se.saw.domain.Valutazione;
import it.unisalento.se.saw.dto.InsegnamentoDto;
import it.unisalento.se.saw.dto.LezioneDto;
import it.unisalento.se.saw.exceptions.ValutazioneNotFoundException;
import it.unisalento.se.saw.restapi.ValutazioneRestController;
	

@RunWith(MockitoJUnitRunner.class)
public class ValutazioneRestControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	private IValutazioneService valutazioneServiceMock;
	
	@Mock
	private IStudenteService studenteServiceMock;
	
	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new ValutazioneRestController(valutazioneServiceMock, studenteServiceMock)).build();
	}
	
	@Test
	public void getAllValutazioneStudenteTest() throws Exception {
		
		List<Valutazione> valutazioni = new ArrayList<Valutazione>();
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
		utente.setIdutente(9);
		utente.setPassword("lucapass");
		
		Utente utente2=new Utente();
		utente2.setNome("Mauro");
		utente2.setCognome("Rosato");
		Date data2 = new Date(1992-03-16);
		utente2.setDataNascita(data);
		utente2.setEmail("mauro@rosato.it");
		utente2.setIdOrigin(0);
		utente2.setIdutente(8);
		utente2.setPassword("mauropass");
		
		Studente studente3 = new Studente();
		studente3.setCodiceFiscale("mrorst31l91e506f");
		studente3.setCorsoDiStudioIdcorsoDiStudio(3);
		studente3.setIdstudente(5);
		studente3.setIndirizzo("via del ciclamino");
		studente3.setMatricola("10087654");
		studente3.setNazione("ITA");
		studente3.setUtente(utente2);
		studenti.add(studente3);
		
		Studente studente2 = new Studente();
		studente2.setCodiceFiscale("mrtrst31l91e506f");
		studente2.setCorsoDiStudioIdcorsoDiStudio(3);
		studente2.setIdstudente(6);
		studente2.setIndirizzo("via del ciclamino");
		studente2.setMatricola("10089654");
		studente2.setNazione("ITA");
		studente2.setUtente(utente);
		studenti.add(studente2);
		
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
		

		
		Lezione lezione2 = new Lezione();
		lezione2.setEvento(evento);
		lezione2.setIdlezione(5);
		
		Valutazione valutazione = new Valutazione();
		valutazione.setIdvalutazione(3);
		valutazione.setLezione(lezione2);
		valutazione.setMaterialeDidatticoIdmaterialeDidattico(2);
		valutazione.setNota("Bella");
		valutazione.setStudente(studente3);
		valutazioni.add(valutazione);
		
		when(studenteServiceMock.getAll()).thenReturn(studenti);
		when(valutazioneServiceMock.getValutazioneStudenteLezione(studente3, lezione2)).thenReturn(valutazioni);
		
		mockMvc.perform(get("/valutazione/getValutazioneStudente/{id}/{idUserStudente}/{oggettoValutato}", lezione2.getIdlezione(), studente3.getUtente().getIdutente(), "lezione"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(0)));

	}
	
	@Test
	public void notFoundValutazioneTest() throws Exception {
		
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
		utente2.setDataNascita(data);
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
		
		Lezione lezione2 = new Lezione();
		lezione2.setEvento(evento);
		lezione2.setIdlezione(5);
		
		Valutazione valutazione = new Valutazione();
		valutazione.setIdvalutazione(3);
		valutazione.setLezione(lezione);
		valutazione.setIdvalutazione(10);
		valutazione.setMaterialeDidatticoIdmaterialeDidattico(2);
		valutazione.setNota("Bella");
		valutazione.setStudente(studente);

		when(valutazioneServiceMock.getValutazioneStudenteLezione(studente, lezione)).thenThrow(new ValutazioneNotFoundException());
		
		mockMvc.perform(get("/valutazione/getValutazioneStudente/{id}/{idUserStudente}/{oggettoValutato}", lezione.getIdlezione(), studente.getUtente().getIdutente(), "lezione"))
		.andExpect(status().is2xxSuccessful());

	}


	@Test
	public void getValutazioneLezioneTest() throws Exception {
		
		List<Lezione> lezioni = new ArrayList<Lezione>();
		List<LezioneDto> lezioniDto = new ArrayList<LezioneDto>();
		List<Valutazione> valutazioni = new ArrayList<Valutazione>();
		List<MaterialeDidattico> materiali = new ArrayList<MaterialeDidattico>();

		
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
		utente2.setDataNascita(data);
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
		
		Valutazione valutazione = new Valutazione();
		valutazione.setIdvalutazione(3);
		valutazione.setLezione(lezione);
		valutazione.setIdvalutazione(10);
		valutazione.setMaterialeDidatticoIdmaterialeDidattico(2);
		valutazione.setNota("Bella");
		valutazione.setStudente(studente);
		valutazioni.add(valutazione);
		
		MaterialeDidattico materiale = new MaterialeDidattico();
		materiale.setDocente(docente);
		materiale.setIdmaterialeDidattico(4);
		materiale.setLezione(lezione);
		materiale.setLink("www.google.com");
		materiale.setNome("materiale");
		materiali.add(materiale);
		
		when(valutazioneServiceMock.getValutazioneLezione(lezione)).thenReturn(valutazioni);
						
		mockMvc.perform(get("/valutazione/getValutazione/{id}/{oggettoValutato}", 4, "matDidattico"))
    	.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$[*]", hasSize(0)));

	}
}