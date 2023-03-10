package demo.devsu.controllers;

import demo.devsu.dto.MovimientosDto;
import demo.devsu.entities.Cuenta;
import demo.devsu.entities.Movimiento;
import demo.devsu.repositories.CuentaRepo;

import demo.devsu.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;


@RestController
@RequestMapping("/cuentas")
public class CuentasController {
    @Autowired
    private MovimientosService movimientosService;
    
    @Autowired
    private CuentaRepo cuentaRepository;

    @GetMapping({"/", ""})
    public ResponseEntity<List<Cuenta>> obtenerMovimientos() {
        return new ResponseEntity(cuentaRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping({"/", ""})
    public ResponseEntity<String> realizarMovimiento(@RequestBody MovimientosDto movimientoDto) {
        try {
           movimientosService.crearMovimiento(movimientoDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);//el mensaje es dinamico, depende de la excepcion
        }
        return new ResponseEntity<>("Creado", HttpStatus.CREATED);
    }  
}