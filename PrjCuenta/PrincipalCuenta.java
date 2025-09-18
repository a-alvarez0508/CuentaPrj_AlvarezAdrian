import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1;

        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("9) Cambiar nombre de cuenta");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();

            switch (op) {
                case "1": { 
                    System.out.println("Tipo de cuenta:");
                    System.out.println("1) Con nombre y saldo inicial");
                    System.out.println("2) Solo con saldo inicial (sin nombre)");
                    System.out.print("Elija: ");
                    String tipo = sc.nextLine().trim();
                    
                    Cuenta cuenta;
                    if (tipo.equals("1")) {
                        System.out.print("Nombre del cuenta habiente: ");
                        String nombre = sc.nextLine().trim();
                        System.out.print("Saldo inicial: ");
                        String saldoStr = sc.nextLine().trim();
                        try {
                            double saldoInicial = Double.parseDouble(saldoStr);
                            cuenta = new Cuenta(nombre, saldoInicial);
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido, se usará saldo 0.0");
                            cuenta = new Cuenta(nombre, 0.0);
                        }
                    } else if (tipo.equals("2")) {
                        System.out.print("Saldo inicial: ");
                        String saldoStr = sc.nextLine().trim();
                        try {
                            double saldoInicial = Double.parseDouble(saldoStr);
                            cuenta = new Cuenta(saldoInicial);
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido, se usará saldo 0.0");
                            cuenta = new Cuenta(0.0);
                        }
                    } else {
                        System.out.println("Opción inválida, creando cuenta con saldo 0.0");
                        cuenta = new Cuenta(0.0);
                    }
                    
                    cuentas.add(cuenta);
                    actual = cuentas.size() - 1;
                    System.out.println("Cuenta creada y seleccionada (índice " + actual + ").");
                    break;
                }
                case "2": { 
                    System.out.println("Cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": {
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas.");
                    } else {
                        System.out.println("\n--- CUENTAS ---");
                        System.out.println("Índice | Código Cuenta | Saldo");
                        System.out.println("-------------------------------");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %-12s | %.2f%n",
                                    i,
                                    c.getCodCuenta(),
                                    c.getSaldo());
                        }
                    }
                    break;
                }
                case "4": { 
                    if (cuentas.isEmpty()) {
                        System.out.println("Cree una cuenta primero.");
                        break;
                    }
                    System.out.print("Índice de la cuenta a seleccionar: ");
                    String idxS = sc.nextLine().trim();
                    try {
                        int idx = Integer.parseInt(idxS);
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta índice " + actual + " seleccionada.");
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }
                case "5": { 
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    String montoStr = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(montoStr);
                        Cuenta cuenta = cuentas.get(actual);
                        double nuevoSaldo = cuenta.depositar(monto);
                        System.out.printf("Depósito realizado. Nuevo saldo: %.2f%n", nuevoSaldo);
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }
                case "6": { 
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    String montoStr = sc.nextLine().trim();
                    try {
                        double monto = Double.parseDouble(montoStr);
                        Cuenta cuenta = cuentas.get(actual);
                        double saldoAnterior = cuenta.getSaldo();
                        double nuevoSaldo = cuenta.retirar(monto);
                        if (nuevoSaldo == saldoAnterior) {
                            System.out.println("Fondos insuficientes para el retiro.");
                        } else {
                            System.out.printf("Retiro realizado. Nuevo saldo: %.2f%n", nuevoSaldo);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Monto inválido.");
                    }
                    break;
                }
                case "7": { 
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    Cuenta cuenta = cuentas.get(actual);
                    System.out.printf("Saldo actual: %.2f%n", cuenta.getSaldo());
                    break;
                }
                case "8": { 
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                case "9": { 
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Nuevo nombre del cuenta habiente: ");
                    String nuevoNombre = sc.nextLine().trim();
                    cuentas.get(actual).setNombreCuentaHabiente(nuevoNombre);
                    System.out.println("Nombre actualizado correctamente.");
                    break;
                }
                case "0": {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}