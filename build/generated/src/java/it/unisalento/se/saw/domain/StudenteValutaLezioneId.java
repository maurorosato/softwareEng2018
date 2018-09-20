package it.unisalento.se.saw.domain;
// Generated 18-set-2018 10.49.46 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StudenteValutaLezioneId generated by hbm2java
 */
@Embeddable
public class StudenteValutaLezioneId  implements java.io.Serializable {


     private int studenteIdstudente;
     private int lezioneIdlezione;

    public StudenteValutaLezioneId() {
    }

    public StudenteValutaLezioneId(int studenteIdstudente, int lezioneIdlezione) {
       this.studenteIdstudente = studenteIdstudente;
       this.lezioneIdlezione = lezioneIdlezione;
    }
   


    @Column(name="studente_idstudente", nullable=false)
    public int getStudenteIdstudente() {
        return this.studenteIdstudente;
    }
    
    public void setStudenteIdstudente(int studenteIdstudente) {
        this.studenteIdstudente = studenteIdstudente;
    }


    @Column(name="lezione_idlezione", nullable=false)
    public int getLezioneIdlezione() {
        return this.lezioneIdlezione;
    }
    
    public void setLezioneIdlezione(int lezioneIdlezione) {
        this.lezioneIdlezione = lezioneIdlezione;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof StudenteValutaLezioneId) ) return false;
		 StudenteValutaLezioneId castOther = ( StudenteValutaLezioneId ) other; 
         
		 return (this.getStudenteIdstudente()==castOther.getStudenteIdstudente())
 && (this.getLezioneIdlezione()==castOther.getLezioneIdlezione());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStudenteIdstudente();
         result = 37 * result + this.getLezioneIdlezione();
         return result;
   }   


}

