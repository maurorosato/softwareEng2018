package it.unisalento.se.saw.domain;
// Generated 10-lug-2018 15.11.51 by Hibernate Tools 5.2.0.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Utente generated by hbm2java
 */
@Entity
@Table(name="utente"
    ,catalog="mydb"
)
public class Utente  implements java.io.Serializable {


     private Integer idutente;
     private String nome;
     private String cognome;
     private String email;
     private Date dataNascita;
     private String password;
     private int idOrigin;
     private Set<NumeroTelefono> numeroTelefonos = new HashSet<NumeroTelefono>(0);
     private Set<Studente> studentes = new HashSet<Studente>(0);
     private Set<SegreteriaDidattica> segreteriaDidatticas = new HashSet<SegreteriaDidattica>(0);
     private Set<Docente> docentes = new HashSet<Docente>(0);

    public Utente() {
    }

	
    public Utente(String nome, String cognome, String email, Date dataNascita, String password, int idOrigin) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.password = password;
        this.idOrigin = idOrigin;
    }
    public Utente(String nome, String cognome, String email, Date dataNascita, String password, int idOrigin, Set<NumeroTelefono> numeroTelefonos, Set<Studente> studentes, Set<SegreteriaDidattica> segreteriaDidatticas, Set<Docente> docentes) {
       this.nome = nome;
       this.cognome = cognome;
       this.email = email;
       this.dataNascita = dataNascita;
       this.password = password;
       this.idOrigin = idOrigin;
       this.numeroTelefonos = numeroTelefonos;
       this.studentes = studentes;
       this.segreteriaDidatticas = segreteriaDidatticas;
       this.docentes = docentes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idutente", unique=true, nullable=false)
    public Integer getIdutente() {
        return this.idutente;
    }
    
    public void setIdutente(Integer idutente) {
        this.idutente = idutente;
    }

    
    @Column(name="nome", nullable=false, length=45)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Column(name="cognome", nullable=false, length=45)
    public String getCognome() {
        return this.cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    
    @Column(name="email", nullable=false, length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dataNascita", nullable=false, length=10)
    public Date getDataNascita() {
        return this.dataNascita;
    }
    
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    
    @Column(name="password", nullable=false, length=45)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="idOrigin", nullable=false)
    public int getIdOrigin() {
        return this.idOrigin;
    }
    
    public void setIdOrigin(int idOrigin) {
        this.idOrigin = idOrigin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="utente")
    public Set<NumeroTelefono> getNumeroTelefonos() {
        return this.numeroTelefonos;
    }
    
    public void setNumeroTelefonos(Set<NumeroTelefono> numeroTelefonos) {
        this.numeroTelefonos = numeroTelefonos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="utente")
    public Set<Studente> getStudentes() {
        return this.studentes;
    }
    
    public void setStudentes(Set<Studente> studentes) {
        this.studentes = studentes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="utente")
    public Set<SegreteriaDidattica> getSegreteriaDidatticas() {
        return this.segreteriaDidatticas;
    }
    
    public void setSegreteriaDidatticas(Set<SegreteriaDidattica> segreteriaDidatticas) {
        this.segreteriaDidatticas = segreteriaDidatticas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="utente")
    public Set<Docente> getDocentes() {
        return this.docentes;
    }
    
    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }




}


