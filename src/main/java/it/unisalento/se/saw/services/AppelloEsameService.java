package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.IAppelloEsame;
import it.unisalento.se.saw.domain.AppelloEsame;
import it.unisalento.se.saw.exceptions.AppelloEsameNotFoundException;
import it.unisalento.se.saw.repositories.AppelloEsameRepository;


@Service
public class AppelloEsameService implements IAppelloEsame {
	
	@Autowired
	AppelloEsameRepository appelloEsameRepository;
	
	@Override
	public List<AppelloEsame> getAll() throws AppelloEsameNotFoundException {
		return appelloEsameRepository.findAll();
	}

}
