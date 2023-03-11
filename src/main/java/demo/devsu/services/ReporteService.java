package demo.devsu.services;

import demo.devsu.dto.ReporteDto;
import demo.devsu.entities.enums.TipoMovimiento;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.devsu.entities.Cuenta;
import java.time.LocalDate;
import java.util.List;


@Service
public class ReporteService {

    @Autowired
    private EntityManager entityManager;

    public ReporteDto estadoDeCuenta(Integer id, LocalDate fechaInicial, LocalDate fechaFinal) {

        try {
            if (fechaInicial.isAfter(fechaFinal)) {
                throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
            }

            //query para la suma de los tipos de movimientos de un cliente
            String queryMovimientos = "SELECT SUM(m.valor) FROM Cliente cl JOIN cl.cuentas c JOIN c.movimientos m WHERE cl.id = :id" +
                                      " AND m.tipoMovimiento = :tipoMovimiento AND m.fecha BETWEEN :fecha1 AND  :fecha2";

            //a continuacion  utilizamos el query para obtener los valores de los debitos y creditos
            Long totalCredito = (Long) entityManager.createQuery(queryMovimientos)
                                                            .setParameter("id", id)
                                                            .setParameter("fecha1", fechaInicial)
                                                            .setParameter("fecha2", fechaFinal)
                                                            .setParameter("tipoMovimiento", TipoMovimiento.CREDITO)
                                                            .getResultList().get(0);

            Long totalDebito = (Long) entityManager.createQuery(queryMovimientos)
                                                    .setParameter("id", id)
                                                    .setParameter("fecha1", fechaInicial).setParameter("fecha2", fechaFinal)
                                                    .setParameter("tipoMovimiento", TipoMovimiento.DEBITO)
                                                    .getResultList().get(0);

            String queryCuentas = "SELECT c FROM Cuenta c JOIN c.movimientos m WHERE c.cliente.id = :id AND m.fecha BETWEEN :fecha1 AND :fecha2";

            List<Cuenta> cuentas = entityManager.createQuery(queryCuentas, Cuenta.class)
                                                .setParameter("id", id)
                                                .setParameter("fecha1", fechaInicial)
                                                .setParameter("fecha2", fechaFinal)
                                                .getResultList();


            ReporteDto reporte = new ReporteDto();

            totalCredito = (totalCredito==null) ? 0 : totalCredito;
            totalDebito = (totalDebito==null) ? 0 : totalDebito;

            reporte.setTotalCredito(totalCredito);
            reporte.setTotalDebito(totalDebito);
            reporte.setCuentas(cuentas);
            reporte.setFechaInicial(fechaInicial);
            reporte.setFechaFinal(fechaFinal);

            return reporte;

        } catch (Exception e) {
            // manejo de la excepción
            System.out.println("Error al generar el reporte: " + e.getMessage());
            return null; //o cualquier otra acción que necesites hacer
        }
    }
}




//traer todas las cuentas de un cliente PROBANDO EN WORKBENCH
//SELECT *
//FROM cuenta
//JOIN movimiento
//WHERE cuenta.cliente_id = 1 AND movimiento.cuenta_id = cuenta.id AND movimiento.fecha BETWEEN '2016-03-08' AND '2023-03-11'

//query para obtener el total de debitos y creditos
// de todos los movimientos de cada cuenta con el id del cliente PROBANDO EN WORKBENCH

//SELECT SUM(movimiento.valor)
//FROM clientes
//JOIN cuenta
//JOIN movimiento
//WHERE clientes.id = cuenta.cliente_id AND cuenta.id = movimiento.cuenta_id AND movimiento.tipo = "CREDITO" AND movimiento.fecha BETWEEN '2016-03-08' AND '2023-03-11'

//SELECT SUM(movimiento.valor)
//FROM clientes
//JOIN cuenta
//JOIN movimiento
//WHERE clientes.id = cuenta.cliente_id AND cuenta.id = movimiento.cuenta_id AND movimiento.tipo = "DEBITO" AND movimiento.fecha BETWEEN '2016-03-08' AND '2023-03-11'


