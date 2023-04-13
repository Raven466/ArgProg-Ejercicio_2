
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // 1. Por parámetro ponga 3 números y una letra que represente
        // ascendente o descendente y los muestre ordenados por tal criterio
        System.out.println("Ejercicio 1");
        int primerValor = 15;
        int segundoValor = 3;
        int tercerValor = 22;
        ordenarValores(primerValor, segundoValor, tercerValor, 'a');
        ordenarValores(primerValor, segundoValor, tercerValor, 'd');

        // 2. Haga una main donde en variable se escriba la ruta de un archivo. Ese archivo debe
        // contener números. El programa debe escribir por consola la suma de esos números
        System.out.println("Ejercicio 2");
        System.out.println(leerYCalcular("src/archivoEjemplo.txt", true));
//        leerYMostrar("src/archivoEjemplo.txt");
//
//        // 2.a. Al programa anterior agreguele un parámetro para que la operación pueda ser
//        // suma o multiplicación.
        System.out.println("Ejercicio 2a");
        boolean esSuma = true;
        double resultado = leerYCalcular("src/numerosEj2.dat", esSuma);
        System.out.println(resultado);
//
//        // 3. Llamar la función del ejercicio 2, que por parámetro se pueda elegir si es una
//        // suma o multiplicación, y 2 archivos, uno para la entrada y otro para la salida.
        System.out.println("Ejercicio 3");
        esSuma = true;
        leerYEscribir("src/numerosEj3.dat", "src/resultadoEj3.dat", esSuma);
    }

    static void ordenarValores(int a, int b, int c, char orden) {
        boolean esAscendente = (orden == 'a');

        if (esAscendente) {
            System.out.print("Orden ascendente: ");
        } else {
            System.out.print("Orden descendente: ");
        }

        if (estaOrdenado(a, b, esAscendente) && estaOrdenado(a, c, esAscendente)) {
            if (estaOrdenado(b, c, esAscendente)) {
                System.out.println(a + " " + b + " " + c);
            } else {
                System.out.println(a + " " + c + " " + b);
            }
        } else if (estaOrdenado(b, a, esAscendente) && estaOrdenado(b, c, esAscendente)) {
            if (estaOrdenado(a, c, esAscendente)) {
                System.out.println(b + " " + a + " " + c);
            } else {
                System.out.println(b + " " + c + " " + a);
            }
        } else if (estaOrdenado(a, b, esAscendente)) {
            System.out.println(c + " " + a + " " + b);
        } else {
            System.out.println(c + " " + b + " " + a);
        }
    }

    static boolean estaOrdenado(int a, int b, boolean ascendente) {
        return ((ascendente && a < b) || (!ascendente && a > b));
    }

    static void leerYMostrar(String path) {
        try {

            int suma = 0;
            for (String linea : Files.readAllLines(Paths.get(path))) {
                suma += Integer.parseInt(linea);
            }
            System.out.println(suma);

        } catch (Exception e) {
            System.out.println("Error leyendo archivo");
        }
    }

    static double leerYCalcular(String path, boolean sumar) {
        try {

            float resultado = 0;
            for (String linea : Files.readAllLines(Paths.get(path))) {
                if (sumar)
                    resultado = resultado + Float.parseFloat(linea);
                else
                    resultado *= Float.parseFloat(linea);
            }
            return resultado;

        } catch (IOException e) {
            System.out.println("Error leyendo archivo");
            return -1;
        }
    }

    static void leerYEscribir(String pathLectura, String pathEscritura, boolean sumar) {
        double calculo = leerYCalcular(pathLectura, sumar);
        soloEscribir(pathEscritura, calculo);
    }

    /* Acepta path que ya estén creados, que existan */
    static void soloEscribir(String pathEscritura, double numero) {
        try {
            Files.writeString(Paths.get(pathEscritura), Double.toString(numero));
            System.out.println("Salió todo bien");

        } catch (IOException e) {
            System.out.println("Error escribiendo archivo");
        }
    }
}
