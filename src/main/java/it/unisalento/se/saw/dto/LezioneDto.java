package it.unisalento.se.saw.dto;

import java.sql.Date;

public class LezioneDto {

	private String descrizione;
	private Float gradimento;
	private String aula;
	private Date data;
	private int idEvento;
	
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Float getGradimento() {
		return gradimento;
	}
	public void setGradimento(Float gradimento) {
		this.gradimento = gradimento;
	}
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
