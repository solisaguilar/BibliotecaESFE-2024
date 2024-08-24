package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El título es requerido")
    private String titulo;

    @NotBlank(message = "El autor es requerido")
    private String autor;

    private LocalDate fechaPublicacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El título es requerido") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El título es requerido") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "El autor es requerido") String getAutor() {
        return autor;
    }

    public void setAutor(@NotBlank(message = "El autor es requerido") String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
