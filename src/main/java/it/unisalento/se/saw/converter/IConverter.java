package it.unisalento.se.saw.converter;

public interface IConverter {
	public <T> Object convertToDTO(T domainObject);
	public <T> Object convertToDomain(T dtoObject);
}
