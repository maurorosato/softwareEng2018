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
 * MaterialeDidattico generated by hbm2java
 */
@Entity
@Table(name="materialeDidattico"
    ,catalog="mydb"
)
public class MaterialeDidattico  implements java.io.Serializable {


     private Integer idmaterialeDidattico;
     private Docente docente;
     private Lezione lezione;
     private String tipologia;
     private Float valutazioneMedia;

    public MaterialeDidattico() {
    }

	
    public MaterialeDidattico(Docente docente, Lezione lezione, String tipologia) {
        this.docente = docente;
        this.lezione = lezione;
        this.tipologia = tipologia;
    }
    public MaterialeDidattico(Docente docente, Lezione lezione, String tipologia, Float valutazioneMedia) {
       this.docente = docente;
       this.lezione = lezione;
       this.tipologia = tipologia;
       this.valutazioneMedia = valutazioneMedia;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idmaterialeDidattico", unique=true, nullable=false)
    public Integer getIdmaterialeDidattico() {
        return this.idmaterialeDidattico;
    }
    
    public void setIdmaterialeDidattico(Integer idmaterialeDidattico) {
        this.idmaterialeDidattico = idmaterialeDidattico;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="docente_iddocente", nullable=false)
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lezione_idlezione", nullable=false)
    public Lezione getLezione() {
        return this.lezione;
    }
    
    public void setLezione(Lezione lezione) {
        this.lezione = lezione;
    }

    
    @Column(name="tipologia", nullable=false, length=45)
    public String getTipologia() {
        return this.tipologia;
    }
    
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    
    @Column(name="valutazioneMedia", precision=12, scale=0)
    public Float getValutazioneMedia() {
        return this.valutazioneMedia;
    }
    
    public void setValutazioneMedia(Float valutazioneMedia) {
        this.valutazioneMedia = valutazioneMedia;
    }




}


