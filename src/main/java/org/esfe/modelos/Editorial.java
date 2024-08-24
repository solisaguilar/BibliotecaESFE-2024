package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Editoriales")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "La dirección es requerida")
    private String direccion;

    @NotBlank(message = "El telefono es requerido")
    private String telefono;

    @NotBlank(message = "El correoElectronico es requerido")
    private String correoElectronico;

    @NotBlank(message = "El nombre de pais es requeirdo")
    private String pais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es requerido") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es requerido") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "La dirección es requerida") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank(message = "La dirección es requerida") String direccion) {
        this.direccion = direccion;
    }

    public @NotBlank(message = "El telefono es requerido") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El telefono es requerido") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "El correoElectronico es requerido") String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(@NotBlank(message = "El correoElectronico es requerido") String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public @NotBlank(message = "El nombre de pais es requeirdo") String getPais() {
        return pais;
    }

    public void setPais(@NotBlank(message = "El nombre de pais es requeirdo") String pais) {
        this.pais = pais;
    }
}

