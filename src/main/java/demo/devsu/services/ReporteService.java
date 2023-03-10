package demo.devsu.services;

import demo.devsu.dto.ReporteDto;
import demo.devsu.entities.enums.TipoMovimiento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import demo.devsu.entities.Cliente;
import demo.devsu.entities.Cuenta;
import demo.devsu.entities.Movimiento;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private EntityManager entityManager;



    //devolver json con todas las cuentas de un cliente
    public ReporteDto estadoDeCuenta(Integer id, LocalDate fechaInicial, LocalDate fechaFinal)
    {
        //query para la suma de los tipos de movimientos de un cliente
        String queryMovimientos = "SELECT SUM(m.valor) FROM Cliente cl JOIN cl.cuentas c JOIN c.movimientos m WHERE cl.id = :id AND m.tipoMovimiento = :tipoMovimiento AND m.fecha BETWEEN :fecha1 AND  :fecha2";


        Long totalCredito = (Long) entityManager.createQuery(queryMovimientos).setParameter("id", id)
                                .setParameter("fecha1", fechaInicial).setParameter("fecha2", fechaFinal)
                                .setParameter("tipoMovimiento", TipoMovimiento.CREDITO).getResultList().get(0);

        Long totalDebito = (Long) entityManager.createQuery(queryMovimientos).setParameter("id", id)
                                .setParameter("fecha1", fechaInicial).setParameter("fecha2", fechaFinal)
                                .setParameter("tipoMovimiento", TipoMovimiento.DEBITO).getResultList().get(0);

        String queryCuentas = "SELECT c FROM Cuenta c JOIN c.movimientos m WHERE c.cliente.id = :id AND m.fecha BETWEEN :fecha1 AND :fecha2";

        List<Cuenta> cuentas =  entityManager.createQuery(queryCuentas).setParameter("id", id)
                            .setParameter("fecha1", fechaInicial).setParameter("fecha2", fechaFinal).getResultList();


        ReporteDto reporte = new ReporteDto();

        reporte.setTotalCredito(totalCredito);
        reporte.setTotalDebito(totalDebito);
        reporte.setCuentas(cuentas);

        return reporte;
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


