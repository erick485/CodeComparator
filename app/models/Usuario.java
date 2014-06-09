package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import java.sql.*;

@Entity
public class Usuario extends Model{
	private static final long serialVersionUID = 1L;
    @Id
    public Integer n_usuario_id;
    public String t_usuario;
    public String t_password;
    public String rep_password;
    @OneToOne
    @JoinColumn(name="n_docente_id")
    public Integer n_docente_id;

    public static Finder<Integer,Usuario> find = new Finder<Integer,Usuario>(
			Integer.class,Usuario.class
	);

    public Usuario(String usuario,Integer id_doc,String password,String rep_password){
        this.t_usuario=usuario;
        this.n_docente_id=id_doc;       
        this.t_password=password;
        this.rep_password=rep_password;
       
    } 

    public static Usuario  create(Integer id_doc,String usuario,String password,String rep_password){
       Usuario user=new Usuario(usuario,id_doc,password,rep_password);
        user.save();
        return user;
    }

    public static Usuario findByEmail(String email){
        return find.where().eq("t_usuario", email).findUnique();
    }

    public static Usuario authenticate(String email,String password){
        return find.where().eq("t_usuario", email)
            .eq("t_password", password).findUnique();
    }
}