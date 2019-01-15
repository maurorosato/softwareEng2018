package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.unisalento.se.saw.domain.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	
	//@Query("SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password")
	public List<Utente> findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
	//public Utente autenticazione(@Param("email") String email,@Param("password") String password);
}
