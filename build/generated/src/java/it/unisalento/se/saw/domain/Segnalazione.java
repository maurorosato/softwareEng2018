package it.unisalento.se.saw.domain;
// Generated 10-lug-2018 15.11.51 by Hibernate Tools 5.2.0.Final


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
 * Segnalazione generated by hbm2java
 */
@Entity
@Table(name="segnalazione"
    ,catalog="mydb"
)
public class Segnalazione  implements java.io.Serializable {


     private Integer idsegnalazione;
     private Aula aula;
     private Operazione operazione;
     private String descrizione;
     private int segreteriaDidatticaIdsegreteriaDidattica;

    public Segnalazione() {
    }

    public Segnalazione(Aula aula, Operazione operazione, String descrizione, int segreteriaDidatticaIdsegreteriaDidattica) {
       this.aula = aula;
       this.operazione = operazione;
       this.descrizione = descrizione;
       this.segreteriaDidatticaIdsegreteriaDidattica = segreteriaDidatticaIdsegreteriaDidattica;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idsegnalazione", unique=true, nullable=false)
    public Integer getIdsegnalazione() {
        return this.idsegnalazione;
    }
    
    public void setIdsegnalazione(Integer idsegnalazione) {
        this.idsegnalazione = idsegnalazione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="aula_idaula", nullable=false)
    public Aula getAula() {
        return this.aula;
    }
    
    public void setAula(Aula aula) {
        this.aula = aula;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="operazione_idoperazione", nullable=false)
    public Operazione getOperazione() {
        return this.operazione;
    }
    
    public void setOperazione(Operazione operazione) {
        this.operazione = operazione;
    }

    
    @Column(name="descrizione", nullable=false)
    public String getDescrizione() {
        return this.descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
    @Column(name="segreteriaDidattica_idsegreteriaDidattica", nullable=false)
    public int getSegreteriaDidatticaIdsegreteriaDidattica() {
        return this.segreteriaDidatticaIdsegreteriaDidattica;
    }
    
    public void setSegreteriaDidatticaIdsegreteriaDidattica(int segreteriaDidatticaIdsegreteriaDidattica) {
        this.segreteriaDidatticaIdsegreteriaDidattica = segreteriaDidatticaIdsegreteriaDidattica;
    }




}


