package org.example.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long idComentario;
    private String contenido;
    private Double valoracion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Comentario() {}

    @Override
    public String toString() {
        return "Comentario{" +
                "idComentario=" + idComentario +
                ", contenido='" + contenido + '\'' +
                ", valoracion=" + valoracion +
                ", usuario=" + usuario.getNombre() +
                '}';
    }
}
