package it.unisalento.se.saw.domain;
// Generated 21-set-2018 9.29.48 by Hibernate Tools 5.2.0.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * StudenteValutaLezione generated by hbm2java
 */
@Entity
@Table(name="studente_valuta_lezione"
    ,catalog="mydb"
)
public class StudenteValutaLezione  implements java.io.Serializable {


     private StudenteValutaLezioneId id;
     private Lezione lezione;
     private Studente studente;
     private Float valutazione;

    public StudenteValutaLezione() {
    }

	
    public StudenteValutaLezione(StudenteValutaLezioneId id, Lezione lezione, Studente studente) {
        this.id = id;
        this.lezione = lezione;
        this.studente = studente;
    }
    public StudenteValutaLezione(StudenteValutaLezioneId id, Lezione lezione, Studente studente, Float valutazione) {
       this.id = id;
       this.lezione = lezione;
       this.studente = studente;
       this.valutazione = valutazione;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="studenteIdstudente", column=@Column(name="studente_idstudente", nullable=false) ), 
        @AttributeOverride(name="lezioneIdlezione", column=@Column(name="lezione_idlezione", nullable=false) ) } )
    public StudenteValutaLezioneId getId() {
        return this.id;
    }
    
    public void setId(StudenteValutaLezioneId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lezione_idlezione", nullable=false, insertable=false, updatable=false)
    public Lezione getLezione() {
        return this.lezione;
    }
    
    public void setLezione(Lezione lezione) {
        this.lezione = lezione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="studente_idstudente", nullable=false, insertable=false, updatable=false)
    public Studente getStudente() {
        return this.studente;
    }
    
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    
    @Column(name="valutazione", precision=12, scale=0)
    public Float getValutazione() {
        return this.valutazione;
    }
    
    public void setValutazione(Float valutazione) {
        this.valutazione = valutazione;
    }




}


