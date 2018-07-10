package it.unisalento.se.saw.domain;
// Generated 10-lug-2018 15.11.51 by Hibernate Tools 5.2.0.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Studente generated by hbm2java
 */
@Entity
@Table(name="studente"
    ,catalog="mydb"
    , uniqueConstraints = @UniqueConstraint(columnNames="codiceFiscale") 
)
public class Studente  implements java.io.Serializable {


     private Integer idstudente;
     private Utente utente;
     private String indirizzo;
     private String livello;
     private String nazione;
     private String codiceFiscale;
     private String matricola;
     private int corsoDiStudioIdcorsoDiStudio;
     private Set<StudenteHasLezione> studenteHasLeziones = new HashSet<StudenteHasLezione>(0);
     private Set<Evento> eventos = new HashSet<Evento>(0);
     private Set<StudenteHasMaterialeDidattico> studenteHasMaterialeDidatticos = new HashSet<StudenteHasMaterialeDidattico>(0);

    public Studente() {
    }

	
    public Studente(Utente utente, String indirizzo, String livello, String nazione, String codiceFiscale, String matricola, int corsoDiStudioIdcorsoDiStudio) {
        this.utente = utente;
        this.indirizzo = indirizzo;
        this.livello = livello;
        this.nazione = nazione;
        this.codiceFiscale = codiceFiscale;
        this.matricola = matricola;
        this.corsoDiStudioIdcorsoDiStudio = corsoDiStudioIdcorsoDiStudio;
    }
    public Studente(Utente utente, String indirizzo, String livello, String nazione, String codiceFiscale, String matricola, int corsoDiStudioIdcorsoDiStudio, Set<StudenteHasLezione> studenteHasLeziones, Set<Evento> eventos, Set<StudenteHasMaterialeDidattico> studenteHasMaterialeDidatticos) {
       this.utente = utente;
       this.indirizzo = indirizzo;
       this.livello = livello;
       this.nazione = nazione;
       this.codiceFiscale = codiceFiscale;
       this.matricola = matricola;
       this.corsoDiStudioIdcorsoDiStudio = corsoDiStudioIdcorsoDiStudio;
       this.studenteHasLeziones = studenteHasLeziones;
       this.eventos = eventos;
       this.studenteHasMaterialeDidatticos = studenteHasMaterialeDidatticos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idstudente", unique=true, nullable=false)
    public Integer getIdstudente() {
        return this.idstudente;
    }
    
    public void setIdstudente(Integer idstudente) {
        this.idstudente = idstudente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="utente_idutente", nullable=false)
    public Utente getUtente() {
        return this.utente;
    }
    
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    
    @Column(name="indirizzo", nullable=false, length=45)
    public String getIndirizzo() {
        return this.indirizzo;
    }
    
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    
    @Column(name="livello", nullable=false, length=45)
    public String getLivello() {
        return this.livello;
    }
    
    public void setLivello(String livello) {
        this.livello = livello;
    }

    
    @Column(name="nazione", nullable=false, length=45)
    public String getNazione() {
        return this.nazione;
    }
    
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    
    @Column(name="codiceFiscale", unique=true, nullable=false, length=45)
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }
    
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    
    @Column(name="matricola", nullable=false, length=45)
    public String getMatricola() {
        return this.matricola;
    }
    
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    
    @Column(name="corsoDiStudio_idcorsoDiStudio", nullable=false)
    public int getCorsoDiStudioIdcorsoDiStudio() {
        return this.corsoDiStudioIdcorsoDiStudio;
    }
    
    public void setCorsoDiStudioIdcorsoDiStudio(int corsoDiStudioIdcorsoDiStudio) {
        this.corsoDiStudioIdcorsoDiStudio = corsoDiStudioIdcorsoDiStudio;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="studente")
    public Set<StudenteHasLezione> getStudenteHasLeziones() {
        return this.studenteHasLeziones;
    }
    
    public void setStudenteHasLeziones(Set<StudenteHasLezione> studenteHasLeziones) {
        this.studenteHasLeziones = studenteHasLeziones;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="studente_has_evento", catalog="mydb", joinColumns = { 
        @JoinColumn(name="studente_idstudente", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="evento_idevento", nullable=false, updatable=false) })
    public Set<Evento> getEventos() {
        return this.eventos;
    }
    
    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="studente")
    public Set<StudenteHasMaterialeDidattico> getStudenteHasMaterialeDidatticos() {
        return this.studenteHasMaterialeDidatticos;
    }
    
    public void setStudenteHasMaterialeDidatticos(Set<StudenteHasMaterialeDidattico> studenteHasMaterialeDidatticos) {
        this.studenteHasMaterialeDidatticos = studenteHasMaterialeDidatticos;
    }




}


