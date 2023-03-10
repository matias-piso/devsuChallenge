package demo.devsu.controllers;

import demo.devsu.dto.ClienteDto;
import demo.devsu.dto.PersonaDto;
import demo.devsu.entities.Cliente;
import demo.devsu.entities.Persona;
import demo.devsu.repositories.ClienteRepo;
import demo.devsu.repositories.PersonaRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ClienteRepo ClienteRepo;

    @Autowired
    private PersonaRepo personaRepo;



    //metodo para obtener todos los clientes
    @GetMapping({"","/"})
    public ResponseEntity<List<Cliente>> cliente(){
            try {
                List<Cliente> clientes = entityManager.createQuery(
                                "SELECT cliente FROM Cliente cliente", Cliente.class)
                        .getResultList();
                return ResponseEntity.ok(clientes);
            } catch (NoResultException e) {
                return ResponseEntity.notFound().build();
            }

        }





    //Metodo para obtener 1 cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
        try {
            TypedQuery<Cliente> query = entityManager.createQuery(
                    "SELECT cliente FROM Cliente cliente WHERE cliente.id = :id", Cliente.class);
            query.setParameter("id", id);
            Cliente cliente = query.getSingleResult();
            return ResponseEntity.ok(cliente);
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }
    }




    //Creo un cliente a partir de un DTO, y lo guardo en la base de datos
    @PostMapping({"/", ""})
    public ResponseEntity<?> crearCliente(@RequestBody @Valid ClienteDto cliente, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                //creo el cliente
                Cliente clienteNuevo = new Cliente( cliente.getNombre(),
                        cliente.getContrasenia(),
                        cliente.getEstado());
                ClienteRepo.save(clienteNuevo);
                return ResponseEntity.created(null).body("cliente creado");

            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al crear el cliente");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un contenido correcto");
        }
    }

    //metodo para borrar un cliente
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        Cliente cliente = entityManager.createQuery(
                        "SELECT cliente FROM Cliente cliente WHERE cliente.estado = 1 AND cliente.id = " + id , Cliente.class)
                .getResultList().get(0);
        if (cliente!= null) {
            cliente.setEstado(false);
            ClienteRepo.save(cliente);
            return ResponseEntity.ok().body("Cliente eliminado");

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Modifico un Cliente a partir del DTO
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable(name = "id") Integer id,
                                              @RequestBody @Valid ClienteDto cliente, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Cliente clienteAModificar = ClienteRepo.findById(id).get();
                Persona personaAModificar = clienteAModificar.getPersona();
                
                if (clienteAModificar == null || personaAModificar == null) {
                    return ResponseEntity.badRequest().body("No se a encontrado el cliente en la base de datos");
                }
                
                
                //verifico que los campos de cliente no esten vacios
                if (cliente.getNombre() == null || cliente.getContrasenia() == null) {
                    return ResponseEntity.badRequest().body("No se puede modificar el cliente");
                }



                personaAModificar.setNombre(cliente.getNombre());
                clienteAModificar.setContrasenia(cliente.getContrasenia());
                clienteAModificar.setEstado(cliente.getEstado());

                return ResponseEntity.ok().body("cliente modificado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("No se a encontrado el cliente en la base de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un cliente valido");
        }

    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarClientePut(@PathVariable(name = "id") Integer id,
                                               @RequestBody @Valid ClienteDto cliente, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Cliente clienteAModificar = ClienteRepo.findById(id).get();
                Persona personaAModificar = clienteAModificar.getPersona();

                //verifico que los campos de cliente no esten vacios
                if (cliente.getNombre() == null || cliente.getContrasenia() == null) {
                    return ResponseEntity.badRequest().body("No se puede modificar el cliente");
                }
                personaAModificar.setNombre(cliente.getNombre());
                clienteAModificar.setContrasenia(cliente.getContrasenia());
                clienteAModificar.setEstado(cliente.getEstado());

                return ResponseEntity.ok().body("cliente modificado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("No se a encontrado el cliente en la base de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un cliente valido");
        }

    }

}