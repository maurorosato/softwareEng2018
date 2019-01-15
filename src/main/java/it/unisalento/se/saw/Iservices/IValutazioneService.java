package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Valutazione;
import it.unisalento.se.saw.exceptions.ValutazioneNotFoundException;

public interface IValutazioneService {
	public void save(Valutazione valutazione) throws ValutazioneNotFoundException;
	public List<Valutazione> getValutazioneStudenteLezione(Studente studente,Lezione lezione) throws ValutazioneNotFoundException;
	public List<Valutazione> getValutazioneLezione(Lezione lezione) throws ValutazioneNotFoundException;
	public List<Valutazione> getValutazioneStudenteMatDidattico(Studente studente,int idMaterialedidattico) throws ValutazioneNotFoundException;
	public List<Valutazione> getValutazioneMatDidattico(int idMaterialedidattico) throws ValutazioneNotFoundException;

}
