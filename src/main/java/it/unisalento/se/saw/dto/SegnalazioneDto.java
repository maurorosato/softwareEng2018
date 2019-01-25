package it.unisalento.se.saw.dto;

import java.util.Date;

public class SegnalazioneDto {
	
	Date data;
	private String image;
	private String nomeAula;
	private String descrizione;
	private String motivazione;
	private int idSegnalazione;
	private String docenteSegnalante;
	private String statoSegnalazione;
	private String oggettoInteressato;
	private int idDocenteSegnalante;
	private int idUserDocenteSegnalante;
	
	public int getIdDocenteSegnalante() {
		return idDocenteSegnalante;
	}
	public void setIdDocenteSegnalante(int idDocenteSegnalante) {
		this.idDocenteSegnalante = idDocenteSegnalante;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdUserDocenteSegnalante() {
		return idUserDocenteSegnalante;
	}
	public void setIdUserDocenteSegnalante(int idUserDocenteSegnalante) {
		this.idUserDocenteSegnalante = idUserDocenteSegnalante;
	}
	public String getDocenteSegnalante() {
		return docenteSegnalante;
	}
	public void setDocenteSegnalante(String docenteSegnalante) {
		this.docenteSegnalante = docenteSegnalante;
	}

	public int getIdSegnalazione() {
		return idSegnalazione;
	}
	public void setIdSegnalazione(int idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getNomeAula() {
		return nomeAula;
	}
	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getOggettoInteressato() {
		return oggettoInteressato;
	}
	public void setOggettoInteressato(String oggettoInteressato) {
		this.oggettoInteressato = oggettoInteressato;
	}
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	public String getStatoSegnalazione() {
		return statoSegnalazione;
	}
	public void setStatoSegnalazione(String statoSegnalazione) {
		this.statoSegnalazione = statoSegnalazione;
	}
}
