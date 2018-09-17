package it.unisalento.se.saw.dto;

public class StrumentazioneDto {
	private int idStrumentazione;
	private String descrizione;
	private String stato;
	private String aulaRiferimento;
	
	public int getIdStrumentazione() {
		return idStrumentazione;
	}
	public void setIdStrumentazione(int idStrumentazione) {
		this.idStrumentazione = idStrumentazione;
	}
	
	public String getAulaRiferimento() {
		return aulaRiferimento;
	}
	public void setAulaRiferimento(String aulaRiferimento) {
		this.aulaRiferimento = aulaRiferimento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
}
