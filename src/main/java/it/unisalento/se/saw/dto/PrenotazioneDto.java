package it.unisalento.se.saw.dto;

import java.sql.Date;

public class PrenotazioneDto {
	
    int idprenotazione;
	String esameOrLezione;
    String tipologiaEsame;
    String descrizioneEsame;
    Date dataInizio;
    Date dataFine;
    String insegnamento;
    String docente;
    int idDocente;
    
    public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	public int getIdprenotazione() {
		return idprenotazione;
	}
	public void setIdprenotazione(int idprenotazione) {
		this.idprenotazione = idprenotazione;
	}
	public String getEsameOrLezione() {
		return esameOrLezione;
	}
	public void setEsameOrLezione(String esameOrLezione) {
		this.esameOrLezione = esameOrLezione;
	}
	public String getTipologiaEsame() {
		return tipologiaEsame;
	}
	public void setTipologiaEsame(String tipologiaEsame) {
		this.tipologiaEsame = tipologiaEsame;
	}
	public String getDescrizioneEsame() {
		return descrizioneEsame;
	}
	public void setDescrizioneEsame(String descrizioneEsame) {
		this.descrizioneEsame = descrizioneEsame;
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
	public String getInsegnamento() {
		return insegnamento;
	}
	public void setInsegnamento(String insegnamento) {
		this.insegnamento = insegnamento;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
    
}
