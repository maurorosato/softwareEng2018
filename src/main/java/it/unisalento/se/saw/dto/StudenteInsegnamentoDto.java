package it.unisalento.se.saw.dto;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Studente;

public class StudenteInsegnamentoDto {

	int idInsegnamento;
	int idStudente;
	Insegnamento insegnamento;
	Studente studente; 
	boolean stato;
	
	public Insegnamento getInsegnamento() {
		return insegnamento;
	}
	public void setInsegnamento(Insegnamento insegnamento) {
		this.insegnamento = insegnamento;
	}
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	
	public int getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
	
	
/*
	int idStudente;
	int idInsegnamento;
	boolean stato;
	
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public int getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
*/
}
