package demo.devsu.dto;

import demo.devsu.entities.Cliente;
import demo.devsu.entities.enums.TipoCuenta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CuentaDto {
    
    private String numero;
    private TipoCuenta tipoCuenta;
    private boolean estadoCuenta;
    private int saldoInicial;
    private Integer clienteId;


    public boolean getEstadoCuenta() {
        return estadoCuenta;
    }
}

/*
    {"numero": "string",
    "tipoCuenta": "CORRIENTE",
    "estadoCuenta": true,
    "saldoInicial": 0,
    "clienteId": 2}
 */
