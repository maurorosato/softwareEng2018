package it.unisalento.se.saw.dto;

import java.util.Date;

public class StudenteDto {
	
	private String nome;
	private String email;
	private String cognome;
	private String nazione;
	private int idStudente;
	private String password;
	private String matricola;
	private Date dataNascita;
	private String indirizzo;
	private int idUserStudente;
	private int idCorsoDiStudio;
	private String codiceFiscale;
	private String corsoDiStudio;
	private String numeroTelefono;
	
	public int getIdUserStudente() {
		return idUserStudente;
	}
	public void setIdUserStudente(int idUserStudente) {
		this.idUserStudente = idUserStudente;
	}
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getCorsoDiStudio() {
		return corsoDiStudio;
	}
	public void setCorsoDiStudio(String corsoDiStudio) {
		this.corsoDiStudio = corsoDiStudio;
	}

	public int getIdCorsoDiStudio() {
		return idCorsoDiStudio;
	}
	public void setIdCorsoDiStudio(int idCorsoDiStudio) {
		this.idCorsoDiStudio = idCorsoDiStudio;
	}
}
