package it.unisalento.se.saw.converter;

public interface IConverter {
	public <T> Object domainToDto(T domainObject);
	public <T> Object dtoToDomain(T dtoObject);
}
