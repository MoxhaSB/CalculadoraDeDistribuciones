package Objects;

import Interfaces.Helper;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class HelperImpl implements Helper {

    /**
     * Scanner para leer datos
     */
    private final Scanner read = new Scanner(System.in);

    /**
     * CONSTRUCTOR
     */
    public HelperImpl(){}

    /**
     * Metodo que valida si se ingreso bien la probabilidad para calcularla
     *
     * @return true si está bien, false si no
     */
    @Override
    public int validarProb() {
        while (true) {
            System.out.println("""
                    \nIngrese la opción que quiere calcular:\s
                    1) <
                    2) <=
                    3) =
                    4) >
                    5) >=
                    6) Volver
                    """);
            int a = validarEntero();
            switch (a) {
                case 1 -> {return 1;}
                case 2 -> {return 2;}
                case 3 -> {return 3;}
                case 4 -> {return 4;}
                case 5 -> {return 5;}
                case 6 -> {return -1;}
                case -1 -> System.out.println("\nIngrese un valor numérico.");
                default -> System.out.println("\nIngrese una opción válida.");
            }
        }
    }

    /**
     * Metodo que calcula el valor de un factorial con  BigInteger
     *
     * @param numero numero a calcular el factorial
     * @return el factorial
     */
    @Override
    public BigInteger factorial(int numero) {
        if (numero < 0) {
            System.out.println("\nEl número debe ser no negativo");
            return BigInteger.valueOf(-1);
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= numero; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Metodo que calcula una combinatoria con BigInteger (numero demasiado grandes)
     *
     * @param n valor de arriba de combinatoria
     * @param k valor de abajo de combinatoria
     * @return el valor de la combinatoria
     */
    @Override
    public BigInteger combinatoria(int n, int k) {
        if(n<0 || k<0){
            System.out.println("\n \"n\" y \"x\" deben ser positivos.");
            return BigInteger.valueOf(-1);
        } else if (k>n) {
            System.out.println("\n \"x\" no puede ser mayor a \"n\". ");
            return BigInteger.valueOf(-1);
        }
        return factorial(n).divide(factorial(k).multiply(factorial(n - k)));
    }

    /**
     * Metodo que convierte un "texto (numerico)" en un formato con coma
     * @param str texto numerico a añadir coma
     * @return numero con coma o -1
     */
    public double convertStringToDouble(String str) {
        try {
            //formato numerico dependiendo del lenguaje, en este caso es 0,2 (coma)
            NumberFormat format = NumberFormat.getInstance(Locale.forLanguageTag("es-ES"));
            //se cambia el formato a lo que quiero
            Number number = format.parse(str);
            return number.doubleValue();
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * Metodo que verifica si un texto ingresado contiene letras o no
     *
     * @return el numero ingresado o -1
     */
    @Override
    public int validarEntero() {
        try {
            return Integer.parseInt(read.next());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
