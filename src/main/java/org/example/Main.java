package org.example;

import org.example.DAO.Service;
import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data.odb");
        Service s = new Service(ObjectDBUtil.getEntityManagerFactory());

        Usuario user = new Usuario();    //Creamos un primer usuario con sus comentarios
        user.setNombre("Usuario 1");
        user.setCorreo("elcorreo@gmail.com");

        Comentario comentario1 = new Comentario();
        comentario1.setContenido("Esta peli e mu mala");
        comentario1.setUsuario(user);
        comentario1.setValoracion(7.5);

        Comentario comentario2 = new Comentario();
        comentario1.setContenido("Esta peli e mu wena");
        comentario1.setUsuario(user);
        comentario1.setValoracion(6.5);

        user.addComentario(comentario1);
        user.addComentario(comentario2);

        s.save(user);                               // guardamos el usuario en la base de datos
//------------------------------------------------------------------------------------------------------

        Usuario user2 = new Usuario();         // Repetimos el proceso con otro usuario de ejemplo
        user2.setNombre("Usuario 2");
        user2.setCorreo("elotrocorreo@gmail.com");

        Comentario comentariox = new Comentario();
        comentariox.setContenido("la vd que nunca me habia preguntao eso");
        comentariox.setUsuario(user2);
        comentariox.setValoracion(9.5);

        Comentario comentarioy = new Comentario();
        comentarioy.setContenido("no voy a recomendarla en la vida");
        comentarioy.setUsuario(user2);
        comentarioy.setValoracion(3.5);


        user2.addComentario(comentariox);
        user2.addComentario(comentarioy);

        s.save(user2);                                  // Guardamos de nuevo el segundo usuario

        System.out.println(s.getUsuarios());        // Comprobamos que se han guardado los usuarios


        System.out.println(s.topComentarios());     // Mostramos los comentarios con valoracion mayor a 8


        // Añadimos un comentario nuevo al primer usuario y llamamos al toString para comprobar que se ha guardado
        s.añadirComentario("Una de las mejores pelis ue he visto, no ve si he llorao", 7.9, user);

        System.out.println(user.toString());

        // Eliminamos el segundo usuario
        s.delete(user2);

    }
}