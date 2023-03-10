package demo.devsu.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import demo.devsu.entities.enums.TipoCuenta;
import demo.devsu.entities.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cuenta")
@Setter
@Getter
@NoArgsConstructor
public class Cuenta  extends Persistencia{
    @Column(name="numero")   
    @NonNull
    private String numero;

    //enum de tipo de cuenta
    @Column(name="tipo")
    @NonNull
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;
    
    
    @Column(name="estado")
    @NonNull
    private boolean EstadoCuenta;
    
    @Column(name="saldoInicial")
    @NonNull
    private int saldoInicial;
    
    
    //relaciones
    @ManyToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @JsonManagedReference //para evitar el bucle infinito
    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;



    //constructores
    public Cuenta(String numero, TipoCuenta tipoCuenta, boolean estadoCuenta, int saldoInicial, Cliente cliente) {
        this.numero = numero;
        this.tipoCuenta = tipoCuenta;
        EstadoCuenta = estadoCuenta;
        this.saldoInicial = saldoInicial;
        this.cliente = cliente;
    }

    //agregamos un movimiento a esa cuenta.
    public void addMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
        movimiento.setCuenta(this);
    }


    //metodos
    public int getSaldo() {
        return saldoInicial;
    }

/*
    public boolean validarLimiteDiario(List<Movimiento> movimientos, Movimiento movimiento) {
        int limiteDiario = 1000;
        int totalDebitos = 0;

        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipoMovimiento() == TipoMovimiento.DEBITO) {
                totalDebitos += movimiento.getValor();
            }
        }

        return totalDebitos <= limiteDiario;
    }
    */

}
