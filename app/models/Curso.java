package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import java.sql.*;
@Entity
public class Curso extends Model{
   private static final long serialVersionUID = 1L;
   @Id
   public Integer n_curso_id;
   public String t_nombre;
   public String n_grupo;
   @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="n_docente_id")
	public Docente docente;

	public static Finder<Integer,Curso> find = new Finder<Integer,Curso>(
			Integer.class,Curso.class
	);

	 public Curso(Docente docente,String nombre,String grupo){
         this.t_nombre=nombre;
         this.n_grupo=grupo;
         this.docente=docente;
       
    } 


  

    public static List<Curso> findCursoDocent(Integer doc){

System.out.print(find);
      /*Query query = JPA.em().createQuery("select * from curso where n_docente_id="+doc+" group by t_nombre");
    List<Article> articles = query.getResultList();
    return articles;*/
    //String sql="select * from curso where n_docente_id=? group by t_nombre";

    //return Curso.find( sql, doc).fetch();
  //  EntityManager em = play.db.jpa.JPA.em();
   // return  em.createNativeQuery("select * from curso where n_docente_id="+doc+" group by t_nombre").getResultList();
     //SqlQuery query=Ebean.createSqlQuery("select * from curso where n_docente_id=:id group by t_nombre");
       //    return query.setParameter("id",doc).findList();
      return find.where()
        .eq("docente.n_docente_id",doc)
        .findList();
        //return find("select * from curso where n_docente_id="+doc+" group by t_nombre").findList();  
    }
public static List<Curso> findnameCursoDocent(Integer doc,String curso){


      return find.where()
        .eq("t_nombre",curso)
        .eq("docente.n_docente_id",doc)
        .findList();
    }
	 public static Curso create(Docente docente,String nombre,String grupo){
         Curso curso= new Curso(docente,nombre,grupo);
    	curso.save();
    	return curso;
    } 

    public static List<Curso> allCurso() {
		return find.all();
	}
}