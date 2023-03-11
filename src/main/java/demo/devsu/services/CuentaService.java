package demo.devsu.services;

import demo.devsu.dto.CuentaDto;
import demo.devsu.entities.Cliente;
import demo.devsu.entities.Cuenta;
import demo.devsu.repositories.ClienteRepo;
import demo.devsu.repositories.CuentaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CuentaService {
    @Autowired
    private CuentaRepo cuentaRepository;

    @Autowired
    private ClienteRepo clienteRepo;

    public void crearCuenta(CuentaDto cuenta) {

       try{

         Cliente cliente = clienteRepo.findById(cuenta.getClienteId()).get();

         Cuenta cuentaNueva = new Cuenta(cuenta.getNumero(), cuenta.getTipoCuenta(),
                                         cuenta.getEstadoCuenta(), cuenta.getSaldoInicial(),
                                         cliente);

         cuentaRepository.save(cuentaNueva);
       } catch (Exception e) {
           throw new RuntimeException("Error al crear la cuenta");
       }


    }


    //delete cliente tiene que darle una baja logica
    @Transactional
    public void deleteCliente(Integer id) {
        Cuenta cuenta = cuentaRepository.findById(id).get();

        if (cuenta == null) {
                throw new IllegalArgumentException("No existe la cuenta");
         }

         cuenta.setEstadoCuenta(false);
      }


    @Transactional
    public void actualizarCuenta(CuentaDto cuenta, Integer id) {
        try{
            Cuenta cuentaAActualizar = cuentaRepository.findById(id).get();

            cuentaAActualizar.setNumero(cuenta.getNumero());
            cuentaAActualizar.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaAActualizar.setEstadoCuenta(cuenta.getEstadoCuenta());
            cuentaAActualizar.setSaldoInicial(cuenta.getSaldoInicial());

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la cuenta");
        }
    }

    @Transactional
        public void editarCuenta(CuentaDto cuenta, Integer id) {
            try{
                Cuenta cuentaAActualizar = cuentaRepository.findById(id).get();

                cuentaAActualizar.setTipoCuenta(cuenta.getTipoCuenta());
                cuentaAActualizar.setEstadoCuenta(cuenta.getEstadoCuenta());

            } catch (Exception e) {
                throw new RuntimeException("Error al actualizar la cuenta");
            }
        }
}
