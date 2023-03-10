package demo.devsu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDto2 {

    private LocalDate fechaInicial;
    
    private LocalDate fechaFinal;


}
