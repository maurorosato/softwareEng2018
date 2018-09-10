package it.unisalento.se.saw.dto;

public class CorsoDiStudioDto {
	
	private Integer idcorsoDiStudio;
	private String nomeCorso;
	private String dipartimento;
	
	public String getNomeCorso() {
		return nomeCorso;
	}
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}
	public String getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}
	public Integer getIdcorsoDiStudio() {
		return idcorsoDiStudio;
	}
	public void setIdcorsoDiStudio(Integer idcorsoDiStudio) {
		this.idcorsoDiStudio = idcorsoDiStudio;
	}
	
	
}
