package demo.devsu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosDto {
        
        private String fecha;
        private String tipoDeMovimiento;
        private int valor;
        private int saldo;
        private Integer cuentaId;
    
}

/*
{
    "fecha": "2020-01-01",
    "tipoDeMovimiento": "CREDITO",
    "valor": 1000,
    "saldo": 1000,
    "cuentaId": 1
}

*/