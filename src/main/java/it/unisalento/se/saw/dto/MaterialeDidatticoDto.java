package it.unisalento.se.saw.dto;

public class MaterialeDidatticoDto {
	int idMaterialeDidattico;
	String nome;
	String link;
	int idEvento;
	int idUtente;
	int idDocente;
	float gradimento;

	
	public int getIdMaterialeDidattico() {
		return idMaterialeDidattico;
	}
	public void setIdMaterialeDidattico(int idMaterialeDidattico) {
		this.idMaterialeDidattico = idMaterialeDidattico;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	public float getGradimento() {
		return gradimento;
	}
	public void setGradimento(float gradimento) {
		this.gradimento = gradimento;
	}

}
