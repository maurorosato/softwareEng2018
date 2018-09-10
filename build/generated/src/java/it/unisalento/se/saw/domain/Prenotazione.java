package it.unisalento.se.saw.domain;
// Generated 10-set-2018 11.50.24 by Hibernate Tools 5.2.0.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Prenotazione generated by hbm2java
 */
@Entity
@Table(name="prenotazione"
    ,catalog="mydb"
)
public class Prenotazione  implements java.io.Serializable {


     private Integer idprenotazione;
     private Docente docente;
     private String orarioInizio;
     private Float durata;
     private Date data;
     private Set<Evento> eventos = new HashSet<Evento>(0);

    public Prenotazione() {
    }

	
    public Prenotazione(Docente docente, Date data) {
        this.docente = docente;
        this.data = data;
    }
    public Prenotazione(Docente docente, String orarioInizio, Float durata, Date data, Set<Evento> eventos) {
       this.docente = docente;
       this.orarioInizio = orarioInizio;
       this.durata = durata;
       this.data = data;
       this.eventos = eventos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idprenotazione", unique=true, nullable=false)
    public Integer getIdprenotazione() {
        return this.idprenotazione;
    }
    
    public void setIdprenotazione(Integer idprenotazione) {
        this.idprenotazione = idprenotazione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="docente_iddocente", nullable=false)
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    
    @Column(name="orarioInizio", length=5)
    public String getOrarioInizio() {
        return this.orarioInizio;
    }
    
    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    
    @Column(name="durata", precision=12, scale=0)
    public Float getDurata() {
        return this.durata;
    }
    
    public void setDurata(Float durata) {
        this.durata = durata;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data", nullable=false, length=10)
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="prenotazione")
    public Set<Evento> getEventos() {
        return this.eventos;
    }
    
    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }




}


