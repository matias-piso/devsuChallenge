package demo.devsu.controllers;

import demo.devsu.dto.CuentaDto;
import demo.devsu.entities.Cuenta;
import demo.devsu.repositories.CuentaRepo;
import demo.devsu.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;


@RestController
@RequestMapping("/cuentas")
public class CuentasController {
    @Autowired
    private CuentaService cuentaService;
    
    @Autowired
    private CuentaRepo cuentaRepository;

    @GetMapping({"/", ""})
    public ResponseEntity<List<Cuenta>> obtenerCuentas() {
        return new ResponseEntity(cuentaRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping({"/", ""})
    public ResponseEntity<String> crearCuenta(@RequestBody CuentaDto cuenta) {
        try {
           cuentaService.crearCuenta(cuenta);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);//el mensaje es dinamico, depende de la excepcion
        }
        return new ResponseEntity<>("Creado", HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCuenta(@PathVariable("id") Integer id,
                                                    @RequestBody CuentaDto cuenta) {
        try {
            cuentaService.actualizarCuenta(cuenta, id);
            return new ResponseEntity<>("Actualizado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> editarCuenta(@PathVariable("id") Integer id,
                                               @RequestBody CuentaDto cuenta) {
        try {
            cuentaService.editarCuenta(cuenta, id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Actualizado", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable("id") Integer id) {
        try {
            cuentaService.deleteCliente(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Eliminado", HttpStatus.OK);
    }
}