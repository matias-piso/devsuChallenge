package demo.devsu.services;

import demo.devsu.entities.Cliente;
import demo.devsu.entities.Cuenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    public void saveCuenta(Cuenta cuenta) {

    }

    public Cuenta getCuentaById(Integer id) {
        return null;
    }


    public Cliente getClienteById(Long clienteId) {
        return null;
    }

    public List<Cuenta> getCuentasByClienteId(Long clienteId) {
        return null;
    }
}
