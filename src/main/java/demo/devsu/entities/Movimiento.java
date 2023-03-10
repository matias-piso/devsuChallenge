package demo.devsu.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import demo.devsu.converters.LocalDateConverter;
import demo.devsu.entities.enums.TipoMovimiento;
import demo.devsu.repositories.MovimientosRepo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
@NoArgsConstructor
public class Movimiento extends Persistencia{
    @Column(name="fecha")
    @NonNull
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fecha;

    @Column(name="tipo")
    @NonNull
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column(name="valor")
    @NonNull
    private int valor;

    @Column(name="saldo")
    @NonNull
    private int saldo;


    //relaciones
    @JsonBackReference //para evitar el bucle infinito
    @ManyToOne
    @JoinColumn(name = "cuentaId", referencedColumnName = "id", nullable = false)
    private Cuenta cuenta;


    //constructores
    public Movimiento(LocalDate fecha, TipoMovimiento tipoMovimiento, int valor, int saldo, Cuenta cuenta) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.cuenta = cuenta;
    }
    
    public Movimiento(LocalDate fecha, TipoMovimiento tipoMovimiento, int valor, int saldo) {
            this.fecha = fecha;
            this.tipoMovimiento = tipoMovimiento;
            this.valor = valor;
            this.saldo = saldo;
        }


    public Integer getClienteId() {
        return cuenta.getCliente().getId();
    }
}
