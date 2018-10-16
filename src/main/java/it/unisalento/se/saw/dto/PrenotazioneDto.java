package it.unisalento.se.saw.dto;

public class PrenotazioneDto {
	
	int idPrenotazione;
	float durata;
	String data;
	String docente;
	
	public int getIdPrenotazione() {
		return idPrenotazione;
	}
	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}
	public float getDurata() {
		return durata;
	}
	public void setDurata(float durata) {
		this.durata = durata;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}


}
