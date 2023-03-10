package demo.devsu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private boolean estado;

    public boolean getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }
}
