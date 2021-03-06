package it.unisalento.se.saw.dto;

public class InsegnamentoDto {
	
	private int cfu;
	private String nome;
	private String docente;
	private String annoCorso;
	private int idUserDocente;
	private int idInsegnamento;
	private String corsoDiStudio;
	
	public int getIdUserDocente() {
		return idUserDocente;
	}
	public void setIdUserDocente(int idUserDocente) {
		this.idUserDocente = idUserDocente;
	}
	public String getAnnoCorso() {
		return annoCorso;
	}
	public void setAnnoCorso(String annoCorso) {
		this.annoCorso = annoCorso;
	}
	public int getCfu() {
		return cfu;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	public int getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCorsoDiStudio() {
		return corsoDiStudio;
	}
	public void setCorsoDiStudio(String corsoDiStudio) {
		this.corsoDiStudio = corsoDiStudio;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}

	
}
