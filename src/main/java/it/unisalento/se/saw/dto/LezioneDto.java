package it.unisalento.se.saw.dto;

import java.util.Date;

public class LezioneDto {
	String aula;
	Date dataFine;
	int idLezione;
	String docente;
	Date dataInizio;
	int idUserDocente;
	double latitudine;
	int idInsegnamento;
	double longitudine;
	String descrizione;
	String insegnamento;
	int idEventoLezione;
	int idPrenotazioneLezione;
	
	public int getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public int getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(int idLezione) {
		this.idLezione = idLezione;
	}
	public int getIdUserDocente() {
		return idUserDocente;
	}
	public void setIdUserDocente(int idUserDocente) {
		this.idUserDocente = idUserDocente;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}
	public double getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
	public int getIdEventoLezione() {
		return idEventoLezione;
	}
	public void setIdEventoLezione(int idEventoLezione) {
		this.idEventoLezione = idEventoLezione;
	}
	public int getIdPrenotazioneLezione() {
		return idPrenotazioneLezione;
	}
	public void setIdPrenotazioneLezione(int idPrenotazioneLezione) {
		this.idPrenotazioneLezione = idPrenotazioneLezione;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public String getInsegnamento() {
		return insegnamento;
	}
	public void setInsegnamento(String insegnamento) {
		this.insegnamento = insegnamento;
	}
}
