package demo.devsu.dto;

import demo.devsu.entities.enums.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosDto {
        
        private LocalDate fecha;
        private TipoMovimiento tipoDeMovimiento;
        private int valor;
        private int saldo;
        private boolean estado;
        private Integer cuentaId;

        public boolean getEstado() {
                return estado;
        }
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