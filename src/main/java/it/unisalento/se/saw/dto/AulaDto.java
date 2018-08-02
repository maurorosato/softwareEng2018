package it.unisalento.se.saw.dto;

public class AulaDto {
	
	private Integer idaula;
	private String edificio;
	private String nome;
	private String stato;
	private Integer capienza;
	
	
	public Integer getCapienza() {
		return capienza;
	}
	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}
	public Integer getIdAula() {
		return idaula;
	}
	public void setIdAula(Integer idAula) {
		this.idaula = idAula;
	}
	
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
}
