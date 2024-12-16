package org.example.DAO;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static EntityManagerFactory emf;
    public Service(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Método que no se pide pero que usaremos para recibir una lista de los usuarios registrados
     * **/
    public List<Usuario> getUsuarios(){
        List<Usuario> salida= new ArrayList<Usuario>(0);
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Usuario> q = em.createQuery("select u from Usuario u", Usuario.class);
            salida = q.getResultList();

        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return salida;
    }

    /**
     * Método que se encarga de registrar y guardar un usuario a la base de datos, correspondiente
     * a la primera historia de usuario
     * **/
    public void save(Usuario u){
        try{
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Método que se encarga de mostrar los comentarios con una valoración mayor que 8, correspondiente
     * a la segunda historia de usuario
     * **/
    public List<Comentario> topComentarios(){
        List<Comentario> salida= new ArrayList<Comentario>(0);
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comentario> q = em.createQuery("select c from Comentario c where c.valoracion > 8.0", Comentario.class);
            salida = q.getResultList();

        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return salida;
    }

    /**
     * Método que se encarga de registrar y guardar comentario nuevo asociandolo al usuario
     * que corresponde, correspondiente a la tercera historia de usuario
     * **/
    public void añadirComentario(String comentario, Double valoracion, Usuario usuario){
        Comentario nuevoC = new Comentario();
        nuevoC.setValoracion(valoracion);
        nuevoC.setUsuario(usuario);
        nuevoC.setContenido(comentario);

        usuario.getComentarios().add(nuevoC);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
    }


    /**
     * Método que se encarga eliminar un usuario de la base de datos, correspondiente
     * a la cuarta historia de usuario
     * **/
    public void delete(Usuario usuario) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.remove(usuario);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
