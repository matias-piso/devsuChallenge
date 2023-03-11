
package demo.devsu.controllers;

import demo.devsu.dto.MovimientosDto;
import demo.devsu.entities.Movimiento;
import demo.devsu.repositories.MovimientosRepo;
import demo.devsu.services.CuentaService;
import demo.devsu.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    // Inyección de dependencias de los servicios necesarios
    @Autowired
    private MovimientosService movimientoService;

    @Autowired
    private CuentaService cuentaService;
    
    @Autowired
    private MovimientosRepo movimientoRepo;

    //obtengo todos los movimientos
    @GetMapping({"/", ""})
    public ResponseEntity<List<Movimiento>> obtenerMovimientos() {
        try {
            List<Movimiento> movimientos = movimientoRepo.findAll();
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping({"/", ""})
    public ResponseEntity<String> crearMovimiento(@RequestBody MovimientosDto movimiento) {
        try {
            movimientoService.crearMovimiento(movimiento);
            return new ResponseEntity<>("Creado", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarMovimiento(@PathVariable("id") Integer id) {
        try {
            movimientoService.eliminarMovimiento(id);
            return new ResponseEntity<>("Eliminado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping({"/{id}"})
    public ResponseEntity<String> actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientosDto movimiento) {
        try {
            movimientoService.actualizarMovimiento(id, movimiento);
            return new ResponseEntity<>("Actualizado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping({"/{id}"})
    public ResponseEntity<String> actualizarMovimientoParcial(@PathVariable("id") Integer id, @RequestBody MovimientosDto movimiento) {
        try {
            movimientoService.actualizarMovimientoParcial(id, movimiento);
            return new ResponseEntity<>("Actualizado", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}



