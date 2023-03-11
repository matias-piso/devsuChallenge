package demo.devsu.dto;

import demo.devsu.entities.enums.Generos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private String nombre;
    private Generos genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private boolean estado;

    public boolean getEstado() {
        return estado;
    }


}

/*
    {
     "nombre": "string",
     "genero": "MASCULINO",
     "edad": 0,
     "identificacion": "string",
     "direccion": "string",
     "telefono": "string",
     "contrasenia": "string",
     "estado": true
    }

*/
