package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import java.sql.*;
@Entity
public class Evaluacion extends Model{
  @Id
  public Integer n_evaluacion_id;
  public String titulo;
  public String tiempo;
  public String curso;
  public String grupo;
  public String descripcion;
  @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="n_docente_id")
   public Docente docente;

   public static Finder<Integer,Alumno> find = new Finder<Integer,Alumno>(
			Integer.class,Alumno.class
	);
     
     public Evaluacion(Docente docente,String titulo,String tiempo,String curso,String grupo,String descrip){
          this.docente=docente;
          this.titulo=titulo;
          this.tiempo=tiempo;
          this.curso=curso;
          this.grupo=grupo;
          this.descripcion=descrip;
     }

    public static Evaluacion create(Docente docente,String titulo,String tiempo,String curso,String grupo,String descrip){
    	Evaluacion eval=new Evaluacion(docente,titulo,tiempo,curso,grupo,descrip);
    	eval.save();
    	return eval;
    }
}