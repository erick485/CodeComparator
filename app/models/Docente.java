package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import java.sql.*;

@Entity
public class Docente extends Model{
	private static final long serialVersionUID = 1L;
    @Id
    public Integer n_docente_id;
    public String t_codigo; 
    public String t_nombre;
    public String t_apellido_paterno;
    public String t_apellido_materno;
    @OneToMany(mappedBy="docente", cascade=CascadeType.ALL)
    public List<Curso> cursos;
   
    @OneToMany(mappedBy="docente", cascade=CascadeType.ALL)
    public List<Alumno> alumno;
    public static Finder<Integer,Docente> find = new Finder<Integer,Docente>(
			Integer.class,Docente.class
	);
     
  
    public static Docente create(Docente docente,String nombre,String cod,String ap_paterno,String ap_materno){
          docente.t_nombre=nombre;
          docente.t_codigo=cod;
          docente.t_apellido_paterno=ap_paterno;
          docente.t_apellido_materno=ap_materno;
    	docente.save();
    	return docente;
    } 

    public static Docente findByCod(String cod){
         return find.where().eq("t_codigo",cod).findUnique();
    }
    
    
   /* public static Docente getIdDocente(String nombre,String ap){
      return find.where().eq("t_usuario", email)
            .eq("t_password", password).findUnique();
      }*/
  }