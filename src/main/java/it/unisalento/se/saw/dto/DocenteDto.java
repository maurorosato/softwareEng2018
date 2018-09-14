package it.unisalento.se.saw.dto;

import java.util.Date;
public class DocenteDto {

	private String nome;
	private String cognome;
	private String password;
	private float stipendio;
	private String email;
	private String grado;
	private Integer idDocente;
	private Date dataNascita;
	private Integer idOrigin;
	private String numeroTelefono;

	
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public float getStipendio() {
		return stipendio;
	}
	public void setStipendio(float stipendio) {
		this.stipendio = stipendio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(Integer idDocente) {
		this.idDocente = idDocente;
	}
	public Integer getIdOrigin() {
		return idOrigin;
	}
	public void setIdOrigin(Integer idOrigin) {
		this.idOrigin = idOrigin;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numTelefono) {
		this.numeroTelefono = numTelefono;
	}
	
	
	
	
}
