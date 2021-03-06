package it.unisalento.se.saw.domain;
// Generated 5-feb-2019 15.21.56 by Hibernate Tools 5.2.0.Final


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
     private String nome;
     private String link;

    public MaterialeDidattico() {
    }

	
    public MaterialeDidattico(Docente docente, Lezione lezione) {
        this.docente = docente;
        this.lezione = lezione;
    }
    public MaterialeDidattico(Docente docente, Lezione lezione, String nome, String link) {
       this.docente = docente;
       this.lezione = lezione;
       this.nome = nome;
       this.link = link;
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

    
    @Column(name="nome", length=45)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Column(name="link")
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }




}


