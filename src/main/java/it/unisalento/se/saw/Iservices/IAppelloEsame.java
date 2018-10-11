package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;

public interface IAppelloEsame {
	public List<AppelloEsame> getAll() throws AppelloEsameNotFoundException;
}
