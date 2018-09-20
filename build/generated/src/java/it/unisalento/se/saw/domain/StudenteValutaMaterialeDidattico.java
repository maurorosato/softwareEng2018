package it.unisalento.se.saw.domain;
// Generated 18-set-2018 10.49.46 by Hibernate Tools 5.2.0.Final


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
 * StudenteValutaMaterialeDidattico generated by hbm2java
 */
@Entity
@Table(name="studente_valuta_materialeDidattico"
    ,catalog="mydb"
)
public class StudenteValutaMaterialeDidattico  implements java.io.Serializable {


     private StudenteValutaMaterialeDidatticoId id;
     private Studente studente;
     private Float valutazione;

    public StudenteValutaMaterialeDidattico() {
    }

	
    public StudenteValutaMaterialeDidattico(StudenteValutaMaterialeDidatticoId id, Studente studente) {
        this.id = id;
        this.studente = studente;
    }
    public StudenteValutaMaterialeDidattico(StudenteValutaMaterialeDidatticoId id, Studente studente, Float valutazione) {
       this.id = id;
       this.studente = studente;
       this.valutazione = valutazione;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="studenteIdstudente", column=@Column(name="studente_idstudente", nullable=false) ), 
        @AttributeOverride(name="materialeDidatticoIdmaterialeDidattico", column=@Column(name="materialeDidattico_idmaterialeDidattico", nullable=false) ) } )
    public StudenteValutaMaterialeDidatticoId getId() {
        return this.id;
    }
    
    public void setId(StudenteValutaMaterialeDidatticoId id) {
        this.id = id;
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

