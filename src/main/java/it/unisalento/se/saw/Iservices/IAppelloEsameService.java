package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;

public interface IAppelloEsameService {
	public AppelloEsame getById(int id) throws AppelloEsameNotFoundException;
	public List<AppelloEsame> getAll() throws AppelloEsameNotFoundException;
	public List<AppelloEsame> getAppelliEsameInsegnamento(Insegnamento insegnamento) throws AppelloEsameNotFoundException ;
	public List<AppelloEsame> getAllStudente(int idCorsoStudente, int annoCorso) throws AppelloEsameNotFoundException;
	public AppelloEsame save(AppelloEsame appelloEsame) throws AppelloEsameNotFoundException;
}
