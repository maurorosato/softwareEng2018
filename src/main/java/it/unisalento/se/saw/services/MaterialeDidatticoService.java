package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.IMaterialeDidatticoService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.MaterialeDidattico;
import it.unisalento.se.saw.exceptions.MaterialeDidatticoNotFoundException;
import it.unisalento.se.saw.repositories.MaterialeDidatticoRepository;

@Service
public class MaterialeDidatticoService implements IMaterialeDidatticoService {
	
	@Autowired
	MaterialeDidatticoRepository materialeDidatticoRepository;
	
	@Override
	public List<MaterialeDidattico> getAll() throws MaterialeDidatticoNotFoundException {
		return materialeDidatticoRepository.findAll();
	}

	@Override
	public List<MaterialeDidattico> getAllLezione(Lezione lezione) throws MaterialeDidatticoNotFoundException {
		return materialeDidatticoRepository.getAllLezione(lezione);
	}
	
	@Transactional
	public MaterialeDidattico save(MaterialeDidattico materialeDidattico) {
		return materialeDidatticoRepository.save(materialeDidattico);
	}
}
