package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unisalento.se.saw.Iservices.ICorsoDiStudioService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.exceptions.CorsoDiStudioNotFoundException;
import it.unisalento.se.saw.repositories.CorsoDiStudioRepository;

@Service
public class CorsoDiStudioService implements ICorsoDiStudioService {
	
	@Autowired
	CorsoDiStudioRepository corsoDiStudioRepository;
	
	@Override
	public List<CorsoDiStudio> getAll() throws CorsoDiStudioNotFoundException {
		return corsoDiStudioRepository.findAll();
	}

	@Override
	public CorsoDiStudio save(CorsoDiStudio corso) {
		// TODO Auto-generated method stub
		return corsoDiStudioRepository.save(corso);
	}

	@Override
	public CorsoDiStudio getById(int id) throws CorsoDiStudioNotFoundException {
		// TODO Auto-generated method stub
		return corsoDiStudioRepository.getOne(id);
	}
}
