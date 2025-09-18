import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta {
    private String codCuenta = "cta-";
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;
    
    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        this.nombreCuentaHabiente = nombreCuentaHabiente;
        saldo = pSaldo;
        
        fechaCreacion = determinarFechaActual();
        
        cantCuentasCreadas++;
        codCuenta += cantCuentasCreadas;
        
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
    }
    
    public Cuenta(double pSaldo) {
        this("Sin nombre", pSaldo);
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta() {
        return codCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public double depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            cantDepositosRealizados++;
        }
        return saldo;
    }
    
    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        return saldo;
    }
    
    private boolean validarRetiro(double monto) {
        return monto > 0 && monto <= saldo;
    }
    
    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }
    
    public String toString() {
        return "Cuenta{" +
                "codCuenta='" + codCuenta + '\'' +
                ", saldo=" + saldo +
                ", nombreCuentaHabiente='" + nombreCuentaHabiente + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", cantDepositosRealizados=" + cantDepositosRealizados +
                ", cantRetirosExitososRealizados=" + cantRetirosExitososRealizados +
                '}';
    }
    
    private String determinarFechaActual() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new java.util.Date());
    }
}