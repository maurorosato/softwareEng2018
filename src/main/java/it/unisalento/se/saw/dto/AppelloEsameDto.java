package it.unisalento.se.saw.dto;

import java.util.Date;

public class AppelloEsameDto {
	int idAppelloEsame;
	int idEventoAppelloEsame;
	int idPrenotazioneAppelloEsame;
	String aula;
	String docente;
	String tipologia;
	Date dataFine;
	Date dataInizio;
	String descrizione;
	String insegnamento;
	
	public int getIdAppelloEsame() {
		return idAppelloEsame;
	}
	public void setIdAppelloEsame(int idAppelloEsame) {
		this.idAppelloEsame = idAppelloEsame;
	}
	public int getIdEventoAppelloEsame() {
		return idEventoAppelloEsame;
	}
	public void setIdEventoAppelloEsame(int idEventoAppelloEsame) {
		this.idEventoAppelloEsame = idEventoAppelloEsame;
	}
	public int getIdPrenotazioneAppelloEsame() {
		return idPrenotazioneAppelloEsame;
	}
	public void setIdPrenotazioneAppelloEsame(int idPrenotazioneAppelloEsame) {
		this.idPrenotazioneAppelloEsame = idPrenotazioneAppelloEsame;
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
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getInsegnamento() {
		return insegnamento;
	}
	public void setInsegnamento(String insegnamento) {
		this.insegnamento = insegnamento;
	}
}
