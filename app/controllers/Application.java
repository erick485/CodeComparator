package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.Templates.*;
import views.html.*;

public class Application extends Controller {
        

    public static Result index() {
        return redirect("/codecomparator");
    }
    public static Result codecomparator(){


         return ok(logging.render());
    }
    public static Result crearCuenta(){
        return ok(crearCuenta.render());
    }
     public static Result curso(){
        Usuario user=Usuario.findByUser(session().get("usuario")); 
        

        return ok(registrarGrupo.render(Curso.findCursoDocent(user.n_docente_id)));

    }
     public static Result alumno(){
        Usuario user=Usuario.findByUser(session().get("usuario")); 
    
        return ok(registrarAlum.render(Curso.findCursoDocent(user.n_docente_id)));
    }

    public static Result regisAlum(String cod,String nombre,String ap_paterno,String ap_materno,String curso,String grupo){
       Form<Alumno> alumForm =form(Alumno.class).bindFromRequest();    	
              Usuario user=Usuario.findByUser(session().get("usuario")); 

       if(alumForm.hasErrors()){
                 return badRequest();
        }else{

          if (Alumno.findByCod(cod) != null) {
                flash("cod_alum","El codigo");
               return ok(listAlumno.render(Alumno.findAlumno(user.n_docente_id,curso,grupo)));
          }
          else{
            
                flash("exito_alum","El codigo");
            Alumno.create(Docente.find.byId(user.n_docente_id),cod,curso,grupo,
            nombre,ap_paterno,ap_materno);
            
         //   return redirect(routes.Application.alumno());      
          return ok(listAlumno.render(Alumno.findAlumno(user.n_docente_id,curso,grupo)));
          }
          
        }
    }
   public static Result updateAlum(String cod,String nombre,String ap_paterno,String ap_materno,String curso,String grupo){
               Form<Alumno> alumForm =form(Alumno.class).bindFromRequest();     

             Usuario user=Usuario.findByUser(session().get("usuario"));

         if(alumForm.hasErrors()){
                 return badRequest();
        }else{
             Alumno.edit(cod,nombre,ap_paterno,ap_materno,user.n_docente_id,curso,grupo);

             System.out.print("--"+curso+" "+grupo);
        return ok(listAlumno.render(Alumno.findAlumno(user.n_docente_id,curso,grupo)));

        }                    
     }
    public static Result listAlum(String curso,String grupo){
       
     
       //Aplication.curso1=curso;
       //grupo1=grupo;
         System.out.print(curso+" "+grupo);
        Usuario user=Usuario.findByUser(session().get("usuario"));
             System.out.print("--"+curso+" "+grupo);
        return ok(listAlumno.render(Alumno.findAlumno(user.n_docente_id,curso,grupo)));
    }
    public static Result listAlumnos(String curso,String grupo,Integer id){
       
     System.out.print("eliminar!!!");
         Alumno.find.ref(id).delete();
      
        Usuario user=Usuario.findByUser(session().get("usuario"));
        return ok(listAlumno.render(Alumno.findAlumno(user.n_docente_id,curso,grupo)));
    }
    public static Result alumn(String curso){

        Usuario user=Usuario.findByUser(session().get("usuario"));

        return ok(auxGrupo.render(Curso.findnameCursoDocent(user.n_docente_id,curso)));
    }
    
    public static Result confExam(String curso){

        Usuario user=Usuario.findByUser(session().get("usuario"));

        return ok(auxConfExam.render(Curso.findnameCursoDocent(user.n_docente_id,curso)));
    }
    public static Result historiales(){
        return ok(historiales.render());
    } 

     public static Result confEvaluacion(){
     Usuario user=Usuario.findByUser(session().get("usuario")); 
    
        //return ok(registrarAlum.render());

        return ok(confEval.render(Curso.findCursoDocent(user.n_docente_id)));
    }
    public static Result asigAlum(String tit,String tiempo,String curso,String grupo,String descr,String fech){
        Usuario user=Usuario.findByUser(session().get("usuario"));

        Evaluacion.create(Docente.find.byId(user.n_docente_id),tit,tiempo,curso,grupo,descr,fech);

            

        return redirect(routes.Comparacion.asigAlum(curso,grupo,tit,tiempo));
    }
   
    public static Result compProy(){
        return ok(compProy.render());
    }
    public static Result cambContr(){
        return ok(updateClave.render());
    }
    public static Result deleteCurso(Integer id){
       Curso.find.ref(id).delete();
       return redirect(routes.Application.curso());
    }
    
   

    public static Result regisGrupo(){

        Form<Curso> cursoForm =form(Curso.class).bindFromRequest();
        if(cursoForm.hasErrors()){
                 return badRequest();
        }else{
            System.out.print(cursoForm.get().n_grupo);
            System.out.print(cursoForm.get().t_nombre);
            System.out.print(session().get("usuario"));

            Usuario user=Usuario.findByUser(session().get("usuario"));
            
            Curso.create(Docente.find.byId(user.n_docente_id),cursoForm.get().t_nombre,cursoForm.get().n_grupo);
            
            return redirect(routes.Application.curso());      
 


        }
    
    }
    public static Result authenticate() {

         System.out.print("Hola Mundo");
        
        Form<Usuario> docentForm = form(Usuario.class).bindFromRequest();

        if (docentForm.hasErrors()) {
            return badRequest();
        } else {
            
            if(Usuario.authenticate(docentForm.get().t_usuario,docentForm.get().t_password)==null){
                flash("success","El usuario o la contraseña incorretos");
                return badRequest(logging.render());
             }   
            else{
                session().clear();
                session("usuario", docentForm.get().t_usuario);
                return redirect(routes.Application.home());
            }
        }
    
     }
     public static Result logout() {
        session().clear();
        
        return redirect(
            routes.Application.index()
        );
     }
     public static Result addCuenta() {
        Form<Usuario> docentForm = form(Usuario.class).bindFromRequest();
        Form<Docente> docente=form(Docente.class).bindFromRequest(); 
        if(docentForm.hasErrors()){
             return badRequest();
        } 
        else{
            String aux=docentForm.get().t_password;
            String aux1=docentForm.get().rep_password;

            if(aux.equals(aux1)){
                
            if(Docente.findByCod(docente.get().t_codigo) != null ){
               flash("codi","El codigo");
               return badRequest(crearCuenta.render());
            } else{
                     
            if(Usuario.findByEmail(docentForm.get().t_usuario)==null){
                Docente.create( new Docente(),docente.get().t_nombre,docente.get().t_codigo,docente.get().t_apellido_paterno,docente.get().t_apellido_materno);                
                
                Docente docent=Docente.findByCod(docente.get().t_codigo); 
                 
                session().clear();
                session("usuario",docentForm.get().t_usuario);

                Usuario.create(docent.n_docente_id,docentForm.get().t_usuario,docentForm.get().t_password,docentForm.get().rep_password);
                flash("registro","Se registró correctamente");
                return badRequest(crearCuenta.render());            
             }
            else{
                 
                flash("usua","Usuario ya existe"); 
               return badRequest(crearCuenta.render());
               
               }                 
            }    
                             
            } 
            else{
                
               
                 flash("claves","El campo confirmar contraseña y contraseña deben coincidir");
                 return badRequest(crearCuenta.render());                
           
        }
        }


     }

     public static Result home(){
         return ok(homeCode.render());
     }

}
