package it.unisalento.se.saw.dto;

import java.util.Date;

public class EventoDto {
	
	private int idEvento;
	private String descrizione;
	private Float gradimento;
	private int idAula;
	private String aula;
	private Date dataInizio;
	private Date dataFine;
	private String lezioneOrEsame;
	private String insegnamento;
	private String corso;
	private int idUtente;
	private String docente;
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getLezioneOrEsame() {
		return lezioneOrEsame;
	}
	public void setLezioneOrEsame(String lezioneOrEsame) {
		this.lezioneOrEsame = lezioneOrEsame;
	}
	
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public int getIdAula() {
		return idAula;
	}
	public void setIdAula(int idAula) {
		this.idAula = idAula;
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
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	
	
}
