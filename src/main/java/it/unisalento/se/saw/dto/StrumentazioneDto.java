package it.unisalento.se.saw.dto;

public class StrumentazioneDto {
	private String descrizione;
	private String stato;
	private String aulaRiferimento;
	private int idAulaRiferimento;
	
	public int getIdAulaRiferimento() {
		return idAulaRiferimento;
	}
	public void setIdAulaRiferimento(int idAulaRiferimento) {
		this.idAulaRiferimento = idAulaRiferimento;
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
	public String getAulaRiferimento() {
		return aulaRiferimento;
	}
	public void setAulaRiferimento(String aulaRiferimento) {
		this.aulaRiferimento = aulaRiferimento;
	}
	
	
}