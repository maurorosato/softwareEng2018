package it.unisalento.se.saw.domain;
// Generated 26-set-2018 12.33.12 by Hibernate Tools 5.2.0.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Lezione generated by hbm2java
 */
@Entity
@Table(name="lezione"
    ,catalog="mydb"
)
public class Lezione  implements java.io.Serializable {


     private Integer idlezione;
     private Evento evento;
     private float gradimento;
     private Set<MaterialeDidattico> materialeDidatticos = new HashSet<MaterialeDidattico>(0);
     private Set<StudenteValutaLezione> studenteValutaLeziones = new HashSet<StudenteValutaLezione>(0);

    public Lezione() {
    }

	
    public Lezione(Evento evento, float gradimento) {
        this.evento = evento;
        this.gradimento = gradimento;
    }
    public Lezione(Evento evento, float gradimento, Set<MaterialeDidattico> materialeDidatticos, Set<StudenteValutaLezione> studenteValutaLeziones) {
       this.evento = evento;
       this.gradimento = gradimento;
       this.materialeDidatticos = materialeDidatticos;
       this.studenteValutaLeziones = studenteValutaLeziones;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idlezione", unique=true, nullable=false)
    public Integer getIdlezione() {
        return this.idlezione;
    }
    
    public void setIdlezione(Integer idlezione) {
        this.idlezione = idlezione;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="evento_idevento", nullable=false)
    public Evento getEvento() {
        return this.evento;
    }
    
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    
    @Column(name="gradimento", nullable=false, precision=12, scale=0)
    public float getGradimento() {
        return this.gradimento;
    }
    
    public void setGradimento(float gradimento) {
        this.gradimento = gradimento;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="lezione")
    public Set<MaterialeDidattico> getMaterialeDidatticos() {
        return this.materialeDidatticos;
    }
    
    public void setMaterialeDidatticos(Set<MaterialeDidattico> materialeDidatticos) {
        this.materialeDidatticos = materialeDidatticos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="lezione")
    public Set<StudenteValutaLezione> getStudenteValutaLeziones() {
        return this.studenteValutaLeziones;
    }
    
    public void setStudenteValutaLeziones(Set<StudenteValutaLezione> studenteValutaLeziones) {
        this.studenteValutaLeziones = studenteValutaLeziones;
    }




}


