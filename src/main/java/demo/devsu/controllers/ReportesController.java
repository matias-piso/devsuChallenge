package demo.devsu.controllers;

import demo.devsu.dto.ReporteDto2;
import demo.devsu.repositories.ClienteRepo;
import demo.devsu.repositories.CuentaRepo;
import demo.devsu.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private CuentaRepo cuentaRepository;

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ClienteRepo clienteRepository;

    @PostMapping("/{id}")
    public ResponseEntity<Long> estadoDeCuenta(@PathVariable Integer id,
                                                  @RequestBody ReporteDto2 reporte) {

        LocalDate fecha1 = reporte.getFechaInicial();
        LocalDate fecha2 = reporte.getFechaFinal();

        return new ResponseEntity(reporteService.estadoDeCuenta(id, fecha1, fecha2), HttpStatus.OK);
    }




    //devolver json con todas las cuentas de un cliente

    //con el total de los debitos de todos los movimientos de cada cuenta

    //con el total de los creditos de todos los movimientos de cada cuenta

    //hay que pasar un id de cliente por body y tiene que hacer el cruce de datos con las cuentas y los movimientos
    //haciendo una query para dicha consulta
}




































/*
*
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientosService movimientoService;

    @GetMapping("/cuentas/estado-de-cuenta")
    public ResponseEntity<Map<String, Object>> estadoDeCuenta(
            @RequestParam(name = "fecha-inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam(name = "fecha-fin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            @RequestParam(name = "cliente-id") Long clienteId) {

        try {
            // Obtener el cliente
            Cliente cliente = cuentaService.getClienteById(clienteId);

            // Obtener las cuentas del cliente
            List<Cuenta> cuentas = cuentaService.getCuentasByClienteId(clienteId);

            // Obtener los movimientos dentro del rango de fechas para cada cuenta
            Map<Long, List<Movimiento>> movimientosPorCuenta = new HashMap<>();
            for (Cuenta cuenta : cuentas) {
                List<Movimiento> movimientos = movimientoService.getMovimientosByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
                movimientosPorCuenta.put(Long.valueOf(cuenta.getId()), movimientos);
            }

            // Obtener los saldos iniciales y totales de débitos y créditos para cada cuenta
            Map<Long, Integer> saldosIniciales = new HashMap<>();
            Map<Long, Integer> totalesDebitos = new HashMap<>();
            Map<Long, Integer> totalesCreditos = new HashMap<>();
            for (Cuenta cuenta : cuentas) {
                int saldoInicial = cuenta.getSaldoInicial();
                int totalDebitos = 0;
                int totalCreditos = 0;

                List<Movimiento> movimientos = movimientosPorCuenta.get(cuenta.getId());
                if (movimientos != null) {
                    for (Movimiento movimiento : movimientos) {
                        if (movimiento.getTipoMovimiento() == TipoMovimiento.CREDITO) {
                            totalCreditos += movimiento.getValor();
                        } else {
                            totalDebitos += movimiento.getValor();
                        }
                    }
                }

                saldosIniciales.put(Long.valueOf(cuenta.getId()), saldoInicial);
                totalesDebitos.put(Long.valueOf(cuenta.getId()), totalDebitos);
                totalesCreditos.put(Long.valueOf(cuenta.getId()), totalCreditos);
            }

            // Construir el reporte
            Map<String, Object> reporte = new HashMap<>();
            reporte.put("cliente", cliente);
            reporte.put("fechaInicio", fechaInicio);
            reporte.put("fechaFin", fechaFin);

            List<Map<String, Object>> cuentasReporte = new ArrayList<>();
            for (Cuenta cuenta : cuentas) {
                Map<String, Object> cuentaReporte = new HashMap<>();
                cuentaReporte.put("numero de cuenta", cuenta.getNumero());
                cuentaReporte.put("saldo inicial", saldosIniciales.get(cuenta.getId()));
                cuentaReporte.put("total de débitos", totalesDebitos.get(cuenta.getId()));
                cuentaReporte.put("total de créditos", totalesCreditos.get(cuenta.getId()));
                cuentaReporte.put("saldo final", saldosIniciales.get(cuenta.getId()) + totalesCreditos.get(cuenta.getId()) - totalesDebitos.get(cuenta.getId()));
                cuentaReporte.put("movimientos", movimientosPorCuenta.get(cuenta.getId()));
                cuentasReporte.add(cuentaReporte);
            }
            reporte.put("cuentas", cuentasReporte);

            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();


        }


    }
* */