package it.unisalento.se.saw.domain;
// Generated 13-dic-2018 12.27.28 by Hibernate Tools 5.2.0.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
     private Docente docente;
     private String oggettoInteressato;
     private String motivazione;
     private String statoSegnalazione;
     private String descrizione;
     private Date data;

    public Segnalazione() {
    }

	
    public Segnalazione(Aula aula, Docente docente, String oggettoInteressato, String motivazione, String statoSegnalazione, String descrizione) {
        this.aula = aula;
        this.docente = docente;
        this.oggettoInteressato = oggettoInteressato;
        this.motivazione = motivazione;
        this.statoSegnalazione = statoSegnalazione;
        this.descrizione = descrizione;
    }
    public Segnalazione(Aula aula, Docente docente, String oggettoInteressato, String motivazione, String statoSegnalazione, String descrizione, Date data) {
       this.aula = aula;
       this.docente = docente;
       this.oggettoInteressato = oggettoInteressato;
       this.motivazione = motivazione;
       this.statoSegnalazione = statoSegnalazione;
       this.descrizione = descrizione;
       this.data = data;
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
    @JoinColumn(name="docente_iddocente", nullable=false)
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    
    @Column(name="oggettoInteressato", nullable=false)
    public String getOggettoInteressato() {
        return this.oggettoInteressato;
    }
    
    public void setOggettoInteressato(String oggettoInteressato) {
        this.oggettoInteressato = oggettoInteressato;
    }

    
    @Column(name="motivazione", nullable=false, length=45)
    public String getMotivazione() {
        return this.motivazione;
    }
    
    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    
    @Column(name="statoSegnalazione", nullable=false, length=45)
    public String getStatoSegnalazione() {
        return this.statoSegnalazione;
    }
    
    public void setStatoSegnalazione(String statoSegnalazione) {
        this.statoSegnalazione = statoSegnalazione;
    }

    
    @Column(name="descrizione", nullable=false)
    public String getDescrizione() {
        return this.descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data", length=19)
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }




}


