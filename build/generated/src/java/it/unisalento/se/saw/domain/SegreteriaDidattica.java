package it.unisalento.se.saw.domain;
// Generated Jan 25, 2019 7:44:36 PM by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SegreteriaDidattica generated by hbm2java
 */
@Entity
@Table(name="segreteriaDidattica"
    ,catalog="mydb"
)
public class SegreteriaDidattica  implements java.io.Serializable {


     private Integer idsegreteriaDidattica;
     private Utente utente;
     private String ruolo;
     private float stipendio;

    public SegreteriaDidattica() {
    }

    public SegreteriaDidattica(Utente utente, String ruolo, float stipendio) {
       this.utente = utente;
       this.ruolo = ruolo;
       this.stipendio = stipendio;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idsegreteriaDidattica", unique=true, nullable=false)
    public Integer getIdsegreteriaDidattica() {
        return this.idsegreteriaDidattica;
    }
    
    public void setIdsegreteriaDidattica(Integer idsegreteriaDidattica) {
        this.idsegreteriaDidattica = idsegreteriaDidattica;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="utente_idutente", nullable=false)
    public Utente getUtente() {
        return this.utente;
    }
    
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    
    @Column(name="ruolo", nullable=false, length=45)
    public String getRuolo() {
        return this.ruolo;
    }
    
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    
    @Column(name="stipendio", nullable=false, precision=12, scale=0)
    public float getStipendio() {
        return this.stipendio;
    }
    
    public void setStipendio(float stipendio) {
        this.stipendio = stipendio;
    }




}


