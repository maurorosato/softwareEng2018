package it.unisalento.se.saw.dto;

public class ValutazioneDto {
	int idValutazione;
	float valutazione;
	int idStudente;
	int idLezione;
	int idMaterialeDidattico;
	String nota;
	
	public int getIdValutazione() {
		return idValutazione;
	}
	public void setIdValutazione(int idValutazione) {
		this.idValutazione = idValutazione;
	}
	public float getValutazione() {
		return valutazione;
	}
	public void setValutazione(float valutazione) {
		this.valutazione = valutazione;
	}
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public int getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(int idLezione) {
		this.idLezione = idLezione;
	}
	public int getIdMaterialeDidattico() {
		return idMaterialeDidattico;
	}
	public void setIdMaterialeDidattico(int idMaterialeDidattico) {
		this.idMaterialeDidattico = idMaterialeDidattico;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	
}
