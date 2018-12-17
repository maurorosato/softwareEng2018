package it.unisalento.se.saw.dto;

import java.util.Date;

public class LezioneDto {
	int idLezione;
	int idEventoLezione;
	int idPrenotazioneLezione;
	Date dataInizio;
	Date dataFine;
	float gradimento;
	String aula;
	String docente;
	double latitudine;
	double longitudine;
	String insegnamento;
	
	public int getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(int idLezione) {
		this.idLezione = idLezione;
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
	public float getGradimento() {
		return gradimento;
	}
	public void setGradimento(float gradimento) {
		this.gradimento = gradimento;
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
