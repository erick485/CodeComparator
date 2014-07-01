package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import java.sql.*;
@Entity
public class Alumno extends Model{
  @Id
  public Integer n_alumno_id;
  public String t_codigo;
  public String t_nombre;
  public String t_apellido_paterno;
  public String t_apellido_materno;
  public String t_curso;
  public String t_grupo;
  @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="n_docente_id")
   public Docente docente;

  public static Finder<Integer,Alumno> find = new Finder<Integer,Alumno>(
			Integer.class,Alumno.class
	);



	 public Alumno(Docente docente,String codigo, String curso,String grupo,String name,String ap_paterno,String ap_materno){
         this.t_codigo=codigo;
         this.t_nombre=name;
         this.t_grupo=grupo;
         this.docente=docente;
         this.t_curso=curso;
         this.t_apellido_materno=ap_materno;
         this.t_apellido_paterno=ap_paterno;
    } 
    
    public static Alumno create(Docente docente,String codigo,String curso,String grupo,String name,String ap_paterno,String ap_materno){
    	Alumno alum=new Alumno(docente,codigo,curso,grupo,name,ap_paterno,ap_materno);
        alum.save();
        return alum;
    }

    public static Alumno findByCod(String cod){
         return find.where().eq("t_codigo",cod).findUnique();
    }
   public static Alumno findDetAlum(String cod,Integer docent,String curso,String grupo){
         return find.where().eq("t_curso",curso).eq("t_grupo",grupo)
        .eq("docente.n_docente_id",docent).eq("t_codigo",cod).findUnique();
    }

   public static Alumno edit(String codigo,String name,String ap_paterno,String ap_materno,Integer docent,String curso,String grupo){
       Alumno alum=Alumno.findDetAlum(codigo,docent,curso,grupo);
        alum.t_nombre=name;
        alum.t_apellido_materno=ap_materno;
        alum.t_apellido_paterno=ap_paterno;
        alum.update();
    return alum;
 
   }
    public static List<Alumno> findAlumno(Integer docent,String curso,String grupo){
     return find.where()
        .eq("t_curso",curso)
        .eq("t_grupo",grupo)
        .eq("docente.n_docente_id",docent)
        .findList();
    }
}