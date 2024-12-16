package org.example.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Long idUsuario;
    private String nombre;
    private String correo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Comentario> comentarios = new ArrayList<>();

    public void addComentario(Comentario comentario) {
        comentarios.add(comentario);
        comentario.setUsuario(this);
    }

    public Usuario() {}

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", comentarios=" + comentarios +
                '}';
    }
}
