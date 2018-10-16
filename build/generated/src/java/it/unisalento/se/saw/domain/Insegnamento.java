package it.unisalento.se.saw.domain;
// Generated 16-ott-2018 0.43.39 by Hibernate Tools 5.2.0.Final


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
 * Insegnamento generated by hbm2java
 */
@Entity
@Table(name="insegnamento"
    ,catalog="mydb"
)
public class Insegnamento  implements java.io.Serializable {


     private Integer idinsegnamento;
     private Docente docente;
     private String nome;
     private int cfu;
     private String settoreScientificoDisciplinare;
     private int corsoDiStudioIdcorsoDiStudio;
     private Set<Evento> eventos = new HashSet<Evento>(0);

    public Insegnamento() {
    }

	
    public Insegnamento(Docente docente, String nome, int cfu, String settoreScientificoDisciplinare, int corsoDiStudioIdcorsoDiStudio) {
        this.docente = docente;
        this.nome = nome;
        this.cfu = cfu;
        this.settoreScientificoDisciplinare = settoreScientificoDisciplinare;
        this.corsoDiStudioIdcorsoDiStudio = corsoDiStudioIdcorsoDiStudio;
    }
    public Insegnamento(Docente docente, String nome, int cfu, String settoreScientificoDisciplinare, int corsoDiStudioIdcorsoDiStudio, Set<Evento> eventos) {
       this.docente = docente;
       this.nome = nome;
       this.cfu = cfu;
       this.settoreScientificoDisciplinare = settoreScientificoDisciplinare;
       this.corsoDiStudioIdcorsoDiStudio = corsoDiStudioIdcorsoDiStudio;
       this.eventos = eventos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idinsegnamento", unique=true, nullable=false)
    public Integer getIdinsegnamento() {
        return this.idinsegnamento;
    }
    
    public void setIdinsegnamento(Integer idinsegnamento) {
        this.idinsegnamento = idinsegnamento;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="docente_iddocente", nullable=false)
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    
    @Column(name="nome", nullable=false, length=45)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Column(name="cfu", nullable=false)
    public int getCfu() {
        return this.cfu;
    }
    
    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    
    @Column(name="settoreScientificoDisciplinare", nullable=false, length=45)
    public String getSettoreScientificoDisciplinare() {
        return this.settoreScientificoDisciplinare;
    }
    
    public void setSettoreScientificoDisciplinare(String settoreScientificoDisciplinare) {
        this.settoreScientificoDisciplinare = settoreScientificoDisciplinare;
    }

    
    @Column(name="corsoDiStudio_idcorsoDiStudio", nullable=false)
    public int getCorsoDiStudioIdcorsoDiStudio() {
        return this.corsoDiStudioIdcorsoDiStudio;
    }
    
    public void setCorsoDiStudioIdcorsoDiStudio(int corsoDiStudioIdcorsoDiStudio) {
        this.corsoDiStudioIdcorsoDiStudio = corsoDiStudioIdcorsoDiStudio;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="insegnamento")
    public Set<Evento> getEventos() {
        return this.eventos;
    }
    
    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }




}


