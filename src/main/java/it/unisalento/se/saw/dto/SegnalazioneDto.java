package it.unisalento.se.saw.dto;

import java.sql.Date;

public class SegnalazioneDto {
	
	private String oggettoInteressato;
	private String statoSegnalazione;
	private String motivazione;
	private String nomeAula;
	private Date data;

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNomeAula() {
		return nomeAula;
	}
	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	/*
	public int getAula_idaula() {
		return aula_idaula;
	}
	public void setAula_idaula(int aula_idaula) {
		this.aula_idaula = aula_idaula;
	}
	
	public int getOperazione_idoperazione() {
		return operazione_idoperazione;
	}
	public void setOperazione_idoperazione(int operazione_idoperazione) {
		this.operazione_idoperazione = operazione_idoperazione;
	}
	
	public int getIdSegnalazione() {
		return idsegnalazione;
	}
	public void setIdSegnalazione(Integer integer) {
		this.idsegnalazione = integer;
	}
	*/
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
