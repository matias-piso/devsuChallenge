package demo.devsu.controllers;

import demo.devsu.dto.ClienteDto;
import demo.devsu.entities.Cliente;
import demo.devsu.repositories.ClienteRepo;
import demo.devsu.repositories.PersonaRepo;
import demo.devsu.services.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClienteService clienteService;



    //metodo para obtener todos los clientes
    @GetMapping({"","/"})
    public ResponseEntity<List<Cliente>> cliente(){
            try {
                List<Cliente> clientes = clienteService.obtenerPersonas();
                return ResponseEntity.ok(clientes);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }

        }


    //Metodo para obtener 1 cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
        try {
            
            Cliente cliente = clienteService.obtenerPersonaPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
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


        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().body("Cliente eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    //Modifico un Cliente a partir del DTO
    @PatchMapping("/{id}")
    public ResponseEntity<?> modificarClienteParcial(@PathVariable(name = "id") Integer id,
                                              @RequestBody @Valid ClienteDto cliente, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                clienteService.modificarPersona(id, cliente);
                return ResponseEntity.ok().body("cliente modificado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un cliente valido");
        }

    }

    //Modifico un Cliente a partir del DTO
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable(name = "id") Integer id,
                                               @RequestBody @Valid ClienteDto cliente, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {

                clienteService.modificarPersonaTotal(id, cliente);

                return ResponseEntity.ok().body("cliente modificado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("No se a encontrado el cliente en la base de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un cliente valido");
        }

    }

}