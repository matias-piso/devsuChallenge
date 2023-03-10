
package demo.devsu.services;

import demo.devsu.dto.MovimientosDto;
import demo.devsu.entities.Cuenta;
import demo.devsu.entities.enums.TipoMovimiento;
import demo.devsu.entities.Movimiento;
import demo.devsu.repositories.CuentaRepo;
import demo.devsu.repositories.MovimientosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class MovimientosService {
    @Autowired
    private MovimientosRepo movimientosRepository;

    @Autowired
    private CuentaRepo cuentaRepository;

    private TipoMovimiento tipoMovimiento;

    public void crearMovimiento(MovimientosDto movimiento) {
            if (movimiento.getValor() < 0) {
                throw new IllegalArgumentException("El valor del movimiento no puede ser negativo");
            } else if (movimiento.getValor() == 0) {
                throw new IllegalArgumentException("El valor del movimiento no puede ser cero");
            }
    
            //Obtener la cuenta por medio del id
    
            Cuenta cuenta = cuentaRepository.getById(movimiento.getCuentaId());
            //Optional<Cuenta> optionalCuenta = cuentaRepository.findById(cuentaId);
            //Cuenta cuenta = optionalCuenta.orElseThrow(() -> new IllegalArgumentException("No se encontró la cuenta con ID " + idCuenta));
    
    
            // Verificamos si el tipo de movimiento es crédito o débito
            if (movimiento.getTipoDeMovimiento().equals("CREDITO")) {
    
                int total = cuenta.getSaldoInicial() + movimiento.getValor();
                // Si es crédito, actualizamos el saldo de la cuenta sumando el valor del movimiento
    
                cuenta.setSaldoInicial(total);
    
                // Creamos un nuevo movimiento y lo guardamos
                Movimiento movimientoNuevo = new Movimiento(LocalDate.now(), tipoMovimiento.CREDITO, movimiento.getValor(), total, cuenta);
    
                movimientosRepository.save(movimientoNuevo);
    
                cuenta.addMovimiento(movimientoNuevo);  // Guardamos la cuenta
                cuentaRepository.save(cuenta);
    
    
            } else if (movimiento.getTipoDeMovimiento().equals("DEBITO")) {
    
                // Si es débito, verificamos si el saldo disponible es suficiente para realizar el retiro
                if (cuenta.getSaldoInicial() >= movimiento.getValor()) {
    
                    // Verificamos si el monto del retiro excede el límite diario
                    if (movimiento.getValor() <= 1000 && verificarLimiteDiario(cuenta, movimiento.getValor())) {
    
                        // Si el retiro es válido, actualizamos el saldo de la cuenta restando el valor del movimiento y guardo el movimiento realizado
                        cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimiento.getValor());
                        Movimiento movimientoNuevo = new Movimiento(LocalDate.now(), tipoMovimiento.DEBITO, movimiento.getValor(), cuenta.getSaldoInicial(), cuenta);
    
                        movimientosRepository.save(movimientoNuevo);
                        cuenta.addMovimiento(movimientoNuevo);  // Guardamos la cuenta
                        cuentaRepository.save(cuenta);
    
                    } else {
    
                        // Si el retiro excede el límite diario, lanzamos una excepción
                        throw new IllegalArgumentException("Cupo diario excedido");
                    }
                } else {
    
                        // Si no hay saldo disponible para realizar el retiro, lanzamos una excepción
                        throw new IllegalArgumentException("Saldo no disponible");
                        }
    
                } else {
    
                    // Si el tipo de movimiento no es válido, lanzamos una excepción
                    throw new IllegalArgumentException("Tipo de movimiento no válido");
                } 
            }
    
    
            public List<Cuenta> obtenerMovimientos() {
                return cuentaRepository.findAll();
            }
    
            private boolean verificarLimiteDiario (Cuenta cuenta,int valor){
                Integer totalDiario = cuenta.getMovimientos().stream()
                        .filter(mov -> (mov.getFecha().equals(LocalDate.now())) && (mov.getTipoMovimiento().equals(TipoMovimiento.DEBITO)))
                        .map(Movimiento::getValor)
                        .reduce(0, Integer::sum);
    
                return (1000 - totalDiario) >= valor;
            }

    
    }