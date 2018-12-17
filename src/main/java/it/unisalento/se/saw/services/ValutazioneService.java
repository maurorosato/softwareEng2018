import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.domain.Evento;


@Service
public class ValutazioneService {

	@Autowired
	ValutazioneRepository valutazioneRepository;
	
	@Transactional
	public void save(ValutazioneDto valutazioneDto) {
		valutazioneRepository.save(evento);
	}
}
