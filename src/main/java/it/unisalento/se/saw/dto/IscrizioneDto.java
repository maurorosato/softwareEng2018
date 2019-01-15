package it.unisalento.se.saw.dto;

public class IscrizioneDto {
	int idIscrizione;
	String stato;
	int idUserStudente;
	int idInsegnamento;
	
	public int getIdIscrizione() {
		return idIscrizione;
	}
	public void setIdIscrizione(int idIscrizione) {
		this.idIscrizione = idIscrizione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public int getIdUserStudente() {
		return idUserStudente;
	}
	public void setIdUserStudente(int idUserStudente) {
		this.idUserStudente = idUserStudente;
	}
}
