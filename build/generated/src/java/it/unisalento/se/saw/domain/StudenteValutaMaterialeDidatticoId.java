package it.unisalento.se.saw.domain;
// Generated 16-ott-2018 0.43.39 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StudenteValutaMaterialeDidatticoId generated by hbm2java
 */
@Embeddable
public class StudenteValutaMaterialeDidatticoId  implements java.io.Serializable {


     private int studenteIdstudente;
     private int materialeDidatticoIdmaterialeDidattico;

    public StudenteValutaMaterialeDidatticoId() {
    }

    public StudenteValutaMaterialeDidatticoId(int studenteIdstudente, int materialeDidatticoIdmaterialeDidattico) {
       this.studenteIdstudente = studenteIdstudente;
       this.materialeDidatticoIdmaterialeDidattico = materialeDidatticoIdmaterialeDidattico;
    }
   


    @Column(name="studente_idstudente", nullable=false)
    public int getStudenteIdstudente() {
        return this.studenteIdstudente;
    }
    
    public void setStudenteIdstudente(int studenteIdstudente) {
        this.studenteIdstudente = studenteIdstudente;
    }


    @Column(name="materialeDidattico_idmaterialeDidattico", nullable=false)
    public int getMaterialeDidatticoIdmaterialeDidattico() {
        return this.materialeDidatticoIdmaterialeDidattico;
    }
    
    public void setMaterialeDidatticoIdmaterialeDidattico(int materialeDidatticoIdmaterialeDidattico) {
        this.materialeDidatticoIdmaterialeDidattico = materialeDidatticoIdmaterialeDidattico;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof StudenteValutaMaterialeDidatticoId) ) return false;
		 StudenteValutaMaterialeDidatticoId castOther = ( StudenteValutaMaterialeDidatticoId ) other; 
         
		 return (this.getStudenteIdstudente()==castOther.getStudenteIdstudente())
 && (this.getMaterialeDidatticoIdmaterialeDidattico()==castOther.getMaterialeDidatticoIdmaterialeDidattico());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStudenteIdstudente();
         result = 37 * result + this.getMaterialeDidatticoIdmaterialeDidattico();
         return result;
   }   


}


