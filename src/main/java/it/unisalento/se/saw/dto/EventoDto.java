package it.unisalento.se.saw.dto;

import java.sql.Date;

public class EventoDto {
	
	private int idEvento;
	private String descrizione;
	private Float gradimento;
	private String aula;
	private Date data;
	private String insegnamento;
	private String corso;
	private String docente;
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
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
	public String getInsegnamento() {
		return insegnamento;
	}
	public void setInsegnamento(String insegnamento) {
		this.insegnamento = insegnamento;
	}
	public String getCorso() {
		return corso;
	}
	public void setCorso(String corso) {
		this.corso = corso;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	
	
}
