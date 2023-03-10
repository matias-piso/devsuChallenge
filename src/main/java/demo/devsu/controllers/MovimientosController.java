
package demo.devsu.controllers;

import demo.devsu.entities.Cuenta;
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


}



