package demo.devsu.dto;

import demo.devsu.entities.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDto {

    private Long totalDebito;

    private Long totalCredito;

    private List<Cuenta> cuentas;

    private LocalDate fechaInicial;

    private LocalDate fechaFinal;
}
