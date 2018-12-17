package it.unisalento.se.saw.dto;

public class ValutazioneDto {
	int id;
	int idStudente;
	String oggettoInteressato;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public String getOggettoInteressato() {
		return oggettoInteressato;
	}
	public void setOggettoInteressato(String oggettoInteressato) {
		this.oggettoInteressato = oggettoInteressato;
	}
}
