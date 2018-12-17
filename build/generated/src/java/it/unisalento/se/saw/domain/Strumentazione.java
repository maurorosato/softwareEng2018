package it.unisalento.se.saw.domain;
// Generated 13-dic-2018 12.27.28 by Hibernate Tools 5.2.0.Final


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
 * Strumentazione generated by hbm2java
 */
@Entity
@Table(name="strumentazione"
    ,catalog="mydb"
)
public class Strumentazione  implements java.io.Serializable {


     private Integer idstrumentazione;
     private Aula aula;
     private String descrizione;
     private String stato;

    public Strumentazione() {
    }

    public Strumentazione(Aula aula, String descrizione, String stato) {
       this.aula = aula;
       this.descrizione = descrizione;
       this.stato = stato;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idstrumentazione", unique=true, nullable=false)
    public Integer getIdstrumentazione() {
        return this.idstrumentazione;
    }
    
    public void setIdstrumentazione(Integer idstrumentazione) {
        this.idstrumentazione = idstrumentazione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="aula_idaula", nullable=false)
    public Aula getAula() {
        return this.aula;
    }
    
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    
    @Column(name="descrizione", nullable=false, length=45)
    public String getDescrizione() {
        return this.descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
    @Column(name="stato", nullable=false, length=45)
    public String getStato() {
        return this.stato;
    }
    
    public void setStato(String stato) {
        this.stato = stato;
    }




}


