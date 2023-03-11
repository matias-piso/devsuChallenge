package demo.devsu.entities;

import demo.devsu.entities.enums.Generos;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona extends Persistencia{

    @Column
    private String nombre;
    @Column
    @Enumerated(EnumType.STRING)
    private Generos genero;
    @Column
    private int edad;
    @Column
    private String identificacion;
    @Column
    private String direccion;
    @Column
    private String telefono;

    public Persona(String nombre) {
        super();
        this.nombre = nombre;
    }

    public Persona(String nombre,String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona(String nombre, Generos genero, int edad,  String identificacion, String direccion, String telefono){
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    };
}