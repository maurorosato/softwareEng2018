package it.unisalento.se.saw.domain;
// Generated Jan 25, 2019 7:44:36 PM by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CorsoDiStudio generated by hbm2java
 */
@Entity
@Table(name="corsoDiStudio"
    ,catalog="mydb"
)
public class CorsoDiStudio  implements java.io.Serializable {


     private Integer idcorsoDiStudio;
     private String nomeCorso;
     private String dipartimento;

    public CorsoDiStudio() {
    }

    public CorsoDiStudio(String nomeCorso, String dipartimento) {
       this.nomeCorso = nomeCorso;
       this.dipartimento = dipartimento;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcorsoDiStudio", unique=true, nullable=false)
    public Integer getIdcorsoDiStudio() {
        return this.idcorsoDiStudio;
    }
    
    public void setIdcorsoDiStudio(Integer idcorsoDiStudio) {
        this.idcorsoDiStudio = idcorsoDiStudio;
    }

    
    @Column(name="nomeCorso", nullable=false, length=45)
    public String getNomeCorso() {
        return this.nomeCorso;
    }
    
    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    
    @Column(name="dipartimento", nullable=false, length=45)
    public String getDipartimento() {
        return this.dipartimento;
    }
    
    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }




}


