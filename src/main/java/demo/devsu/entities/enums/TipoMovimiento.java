package demo.devsu.entities.enums;

public enum TipoMovimiento {
    CREDITO,
    DEBITO;

    public static TipoMovimiento getTipoMovimiento(String tipoMovimiento) {
        if (tipoMovimiento.equals("CREDITO")) {
            return CREDITO;
        } else if (tipoMovimiento.equals("DEBITO")) {
            return DEBITO;
        }
        return null;
    }
    
    public boolean equalsIgnoreCase(String debito) {
        if (this.name().equalsIgnoreCase(debito)) {
            return true;
        }
        return false;
    }
}
