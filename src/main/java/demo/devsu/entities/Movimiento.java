package demo.devsu.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import demo.devsu.converters.LocalDateConverter;
import demo.devsu.entities.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDate;


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

    @Column(name="estado")
    @NonNull
    private boolean estado;

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
    public Movimiento(LocalDate fecha, TipoMovimiento tipoMovimiento, int valor, boolean estado, int saldo, Cuenta cuenta) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.estado = estado;
        this.saldo = saldo;
        this.cuenta = cuenta;
    }
    
    public Movimiento(LocalDate fecha, TipoMovimiento tipoMovimiento, int valor, int saldo, boolean estado) {
            this.fecha = fecha;
            this.tipoMovimiento = tipoMovimiento;
            this.valor = valor;
            this.saldo = saldo;
            this.estado = estado;
        }


}
