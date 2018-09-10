package it.unisalento.se.saw.dto;

public class InsegnamentoDto {
	
	private int idInsegnamento;
	private String nome;
	private int cfu;
	private String settoreScientificoDisciplinare;
	private String corsoDiStudio;
	private String docente;
	
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
	
	public String getSettoreScientificoDisciplinare() {
		return settoreScientificoDisciplinare;
	}
	public void setSettoreScientificoDisciplinare(String settoreScientificoDisciplinare) {
		this.settoreScientificoDisciplinare = settoreScientificoDisciplinare;
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
