package it.unisalento.se.saw.domain;
// Generated 10-gen-2019 15.32.49 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NumeroTelefono generated by hbm2java
 */
@Entity
@Table(name="numeroTelefono"
    ,catalog="mydb"
)
public class NumeroTelefono  implements java.io.Serializable {


     private String numeroTelefono;
     private Utente utente;

    public NumeroTelefono() {
    }

    public NumeroTelefono(String numeroTelefono, Utente utente) {
       this.numeroTelefono = numeroTelefono;
       this.utente = utente;
    }
   
     @Id 

    
    @Column(name="numeroTelefono", unique=true, nullable=false, length=45)
    public String getNumeroTelefono() {
        return this.numeroTelefono;
    }
    
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="utente_idutente", nullable=false)
    public Utente getUtente() {
        return this.utente;
    }
    
    public void setUtente(Utente utente) {
        this.utente = utente;
    }




}


