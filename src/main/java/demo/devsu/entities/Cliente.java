package demo.devsu.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "clientes")
@Setter
@Getter
@NoArgsConstructor //se utiliza para evitar el error de que no se encuentra el constructor vacio
public class Cliente extends Persistencia{

    @Column(name = "password")
    private String contrasenia;

    @Column(name = "estado")
    private boolean estadoCliente;

    //relaciones

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Persona persona;

    //la siguiente relacion se utiliza para resolver el servicio de reportes
    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
   private List<Cuenta> cuentas;

  
    //constrcutor para el test unitario
    public Cliente(String nombre, String direccion, String telefono, String contrasenia, boolean estadoCliente, Persona persona) {
        this.contrasenia = contrasenia;
        this.estadoCliente = estadoCliente;
        this.persona = persona;
    }
 

    public Cliente(String nombre, String contrasenia, boolean estado) {
        this.contrasenia = contrasenia;
        this.estadoCliente = estado;
    }

    public void setEstado(boolean b) {
        this.estadoCliente = b; 
    }
}

       