package it.unisalento.se.saw.dto;

public class UtenteLoggatoDto {
	UtenteDto utenteDto;
	DocenteDto docenteDto;
	StudenteDto studenteDto;
	String typeUtente;
	public UtenteDto getUtenteDto() {
		return utenteDto;
	}
	public void setUtenteDto(UtenteDto utenteDto) {
		this.utenteDto = utenteDto;
	}
	public DocenteDto getDocenteDto() {
		return docenteDto;
	}
	public void setDocenteDto(DocenteDto docenteDto) {
		this.docenteDto = docenteDto;
	}
	public StudenteDto getStudenteDto() {
		return studenteDto;
	}
	public void setStudenteDto(StudenteDto studenteDto) {
		this.studenteDto = studenteDto;
	}
	public String getTypeUtente() {
		return typeUtente;
	}
	public void setTypeUtente(String typeUtente) {
		this.typeUtente = typeUtente;
	}
	
	
}
