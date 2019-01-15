package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IAppelloEsameService;
import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.exceptions.DocenteNotFoundException;
import it.unisalento.se.saw.exceptions.LezioneNotFoundException;
import it.unisalento.se.saw.repositories.AppelloEsameRepository;


@Service
public class AppelloEsameService implements IAppelloEsameService {
	
	@Autowired
	AppelloEsameRepository appelloEsameRepository;
	
	@Override
	public List<AppelloEsame> getAll() throws AppelloEsameNotFoundException {
		return appelloEsameRepository.findAll();
	}

	@Transactional
	public AppelloEsame save(AppelloEsame appelloEsame) {
		return appelloEsameRepository.save(appelloEsame);
	}
	
	@Override
	public AppelloEsame getById(int id) throws AppelloEsameNotFoundException {
		return appelloEsameRepository.getOne(id);
	}
	
	@Override
	public List<AppelloEsame> getAllStudente(int idCorsoStudente, int annoCorso) throws AppelloEsameNotFoundException{
		return appelloEsameRepository.getAllStudente(idCorsoStudente, annoCorso);
	}
	
	@Override
	public List<AppelloEsame> getAppelliEsameInsegnamento(Insegnamento insegnamento) throws AppelloEsameNotFoundException  {
		return appelloEsameRepository.getAppelliEsameInsegnamento(insegnamento);
	}
}
