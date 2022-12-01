package com.certus.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Producto" )
public class Producto {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codProducto;

    @NotEmpty(message = "Indicar la nombre")
    private String nombre;

    @NotEmpty(message = "Indicar la precio")
    private String precio;

    public int getCodProducto() {
        return this.codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return this.precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
