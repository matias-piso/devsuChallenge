package demo.devsu.services;

import demo.devsu.dto.ClienteDto;
import demo.devsu.entities.Cliente;
import demo.devsu.entities.Persona;
import demo.devsu.repositories.ClienteRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClienteService {
        @Autowired
        private EntityManager entityManager;

        @Autowired
        private ClienteRepo clienteRepository;



    public List<Cliente> obtenerPersonas() throws Exception {
        List<Cliente> clientes = entityManager.createQuery(
                        "SELECT cliente FROM Cliente cliente", Cliente.class)
                .getResultList();

        if (clientes == null || clientes.isEmpty()) {
            throw new Exception("No hay clientes");
        }

        return clientes;
    }

    public Cliente obtenerPersonaPorId(Integer id)  {
        Cliente cliente = entityManager.find(Cliente.class, id);

        if (cliente == null) {
            throw new IllegalArgumentException("No existe el cliente");
        }

        return cliente;
    }


    public void deleteCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id).get();

        if (cliente == null) {
            throw new IllegalArgumentException("No existe el cliente");
        }

        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void modificarPersona(Integer id, ClienteDto cliente){
      Cliente clienteAModificar = clienteRepository.findById(id).get();
      Persona personaAModificar = clienteAModificar.getPersona();

      if (clienteAModificar == null || personaAModificar == null){
        throw new IllegalArgumentException("No existe el cliente");
      }

      //verifico que los campos de cliente no esten vacios
      if (cliente.getNombre() == "" || cliente.getContrasenia() == "")
      {
        throw new IllegalArgumentException("Los campos son vacios");
      }

      personaAModificar.setNombre(cliente.getNombre());
      clienteAModificar.setContrasenia(cliente.getContrasenia());
      clienteAModificar.setEstado(cliente.getEstado());
    } 
    
    
    
    
    @Transactional
    public void modificarPersonaTotal(Integer id, ClienteDto cliente){
      Cliente clienteAModificar = clienteRepository.findById(id).get();
      Persona personaAModificar = clienteAModificar.getPersona();

      if (clienteAModificar == null || personaAModificar == null){
        throw new IllegalArgumentException("No existe el cliente");
      }

      //verifico que los campos de cliente no esten vacios
      if (cliente.getNombre() == "" || cliente.getContrasenia() == "")
      {
        throw new IllegalArgumentException("Los campos son vacios");
      }

      personaAModificar.setNombre(cliente.getNombre());
      personaAModificar.setGenero(cliente.getGenero());
      personaAModificar.setEdad(cliente.getEdad());
      personaAModificar.setIdentificacion(cliente.getIdentificacion());
      personaAModificar.setDireccion(cliente.getDireccion());
      personaAModificar.setTelefono(cliente.getTelefono());
      clienteAModificar.setContrasenia(cliente.getContrasenia());
      clienteAModificar.setEstado(cliente.getEstado());
    }
    
}