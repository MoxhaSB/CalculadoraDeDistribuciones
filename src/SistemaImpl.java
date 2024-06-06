import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class SistemaImpl implements Sistema{

    /**
     * Scanner para leer datos
     */
    private final Scanner read = new Scanner(System.in);

    /**
     * CONSTRUCTOR
     */
    public SistemaImpl(){
        menuPrincipal();
    }

    /**
     * Metodo que contiene y entrega valores con la distribución binomial
     */
    @Override
    public void binomial() {
        int n;
        String p;

        while (true) {
            System.out.println("""
                    \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            Distribución Binomial        
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    
                    X ~ BINOMIAL ( n , p )
                    
                    |+| n : cantidad de repeticiones del experimento
                    |+| p : probabilidad de éxito
                    
                     \nIngrese una opción: 
                    |1| Calcular una probabilidad
                    |2| Calcular Esperanza
                    |3| Calcular Varianza
                    |4| Salir
                    """);

            switch (validarEntero()){
                case -1: {
                    System.out.println("\nIngrese un valor numérico.");
                    break;
                }
                case 1:
                    System.out.println("""
                                \n------------------------------------
                                   Calcular Probabilidad Binomial
                                ------------------------------------
                                   
                                      C(n,x) * p^x * (1-p)^(n-x)
                                                            
                                Ingrese valor n: 
                                """);

                    n = validarEntero();

                    if (n < 0) {
                        System.out.println("\n|*| \"n\"  debe ser un número entero NO negativo");
                    } else {
                        System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                        p = read.next();
                        double pp = convertStringToDouble(p);

                        if (pp < 0 || pp > 1) {
                            System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                        } else {

                            double resultado = 0;

                            switch (validarProb()) {
                                case 1 -> {
                                    while (true) {
                                        System.out.println("\nIngrese el número a calcular (x < ?): ");
                                        int numero = validarEntero();
                                        //si no es un numero
                                        if (numero == -1) {
                                            System.out.println("\nIngrese un valor numérico.");

                                            //si x es mayor a n
                                        } else if (numero < 0){
                                            System.out.println("\nEl valor de \"x\" no puede ser negativo");
                                        }else if (numero > n){
                                            System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                            //cualquier otro caso
                                        }else {
                                            resultado = 0;
                                            for (int i = 0; i < numero; i++) {
                                                resultado += combinatoria(n, i).doubleValue() * Math.pow(pp, i) * Math.pow(1 - pp, n - i);
                                            }
                                            System.out.println("\nLa probabilidad es : " + resultado);
                                            break;

                                        }
                                    }
                                    return;
                                }
                                case 2 -> {
                                    while (true) {
                                        System.out.println("\nIngrese el número a calcular (x <= ?): ");
                                        int numero = validarEntero();
                                        if (numero == -1) {
                                            System.out.println("\nIngrese un valor numérico.");
                                        } else {
                                            resultado = 0;
                                            for (int i = 0; i <= numero; i++) {
                                                resultado += combinatoria(n, i).doubleValue() * Math.pow(pp, i) * Math.pow(1 - pp, n - i);
                                            }
                                            System.out.println("\nLa probabilidad es : " + resultado);
                                            break;
                                        }
                                    }
                                    return;
                                }
                                case 3 -> {
                                    while (true) {
                                        System.out.println("\nIngrese el número a calcular (x = ?): ");
                                        int numero = validarEntero();
                                        if (numero == -1) {
                                            System.out.println("\nIngrese un valor numérico.");
                                        } else {
                                            resultado = combinatoria(n, numero).doubleValue() * Math.pow(pp, numero) * Math.pow(1 - pp, n - numero);
                                            System.out.println("\nLa probabilidad es : " + resultado);
                                            return;
                                        }
                                    }
                                }
                                case 4 -> {
                                    while (true) {
                                        System.out.println("\nIngrese el número a calcular (x > ?): ");
                                        int numero = validarEntero();
                                        if (numero == -1) {
                                            System.out.println("\nIngrese un valor numérico.");
                                        } else {
                                            resultado = 0;
                                            for (int i = numero + 1; i <= n; i++) {
                                                resultado += combinatoria(n, i).doubleValue() * Math.pow(pp, i) * Math.pow(1 - pp, n - i);
                                            }
                                            if(resultado == 0){
                                                System.out.println("\nLa probabilidad es 0");
                                            }else {
                                                System.out.println("\nLa probabilidad es : " + resultado);
                                            }
                                            break;
                                        }
                                    }
                                    return;
                                }
                                case 5 -> {
                                    while (true) {
                                        System.out.println("\nIngrese el número a calcular (x >= ?): ");
                                        int numero = validarEntero();
                                        if (numero == -1) {
                                            System.out.println("\nIngrese un valor numérico positivo.");
                                        } else {
                                            resultado = 0;
                                            for (int i = numero; i <= n; i++) {
                                                resultado += combinatoria(n, i).doubleValue() * Math.pow(pp, i) * Math.pow(1 - pp, n - i);
                                            }
                                            System.out.println("\nLa probabilidad es : " + resultado);
                                            break;
                                        }
                                    }
                                    return;
                                }
                                case -1 -> {
                                    return;
                                }
                            }
                        }
                    }

                    break;
                case 2:
                    //se calcula la esperanza
                    while(true) {
                        System.out.println("""
                                \n------------------------------------
                                      Calcular Esperanza Binomial
                                ------------------------------------
                                
                                            E[X] = n * p
                                                            
                                Ingrese valor n: 
                                """);
                        n = validarEntero();

                        if (n < 0) {
                            System.out.println("\n|*| \"n\"  debe ser un número entero NO negativo");
                        } else {
                            System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                            p = read.next();
                            double pp = convertStringToDouble(p);

                            if (pp < 0 || pp > 1) {
                                System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                            } else {
                                System.out.println("\nLa esperanza es : " + n * pp);
                                break;
                            }
                        }
                    }
                    break;

                case 3:
                    while(true) {
                        System.out.println("""
                                \n------------------------------------
                                      Calcular Varianza Binomial
                                ------------------------------------
                                
                                      VAR[X] = n * p * ( 1 - p )
                                                            
                                Ingrese valor n: 
                                """);
                        n = validarEntero();

                        if (n < 0) {
                            System.out.println("\n|*| \"n\"  debe ser un número entero NO negativo");
                        } else {
                            System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                            p = read.next();
                            double pp = convertStringToDouble(p);

                            if (pp < 0 || pp > 1) {
                                System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                            } else {
                                System.out.println("\nLa varianza es : " + +((n*pp)*(1-pp)));
                                break;
                            }
                        }
                    }
                    break;

                case 4:
                {return;}

                default:
                    System.out.println("\nIngrese una opción válida.");
            }

        }
    }

    /**
     * Metodo que contiene y entrega valores con la distribución binomial negativa
     */
    @Override
    public void binomialNegativa() {

    }

    /**
     * Metodo que contiene y entrega valores con la distribución hipergeometrica
     */
    @Override
    public void hipergeometrica() {

    }

    /**
     * Metodo que contiene y entrega valores con la distribución Poisson
     */
    @Override
    public void poisson() {

    }

    /**
     * Metodo que contiene y entrega valores con la distribución exponencial
     */
    @Override
    public void exponencial() {

    }

    /**
     * Metodo que contiene y lleva a las calculadoras de distribucioens
     */
    @Override
    public void menuPrincipal() {
        while (true) {
            System.out.println("""
                    \n________   .__           __           .__ ___.                   .__                                 
                    \\______ \\  |__|  _______/  |_ _______ |__|\\_ |__   __ __   ____  |__|  ____    ____    ____    ______
                     |    |  \\ |  | /  ___/\\   __\\\\_  __ \\|  | | __ \\ |  |  \\_/ ___\\ |  | /  _ \\  /    \\ _/ __ \\  /  ___/
                     |    `   \\|  | \\___ \\  |  |   |  | \\/|  | | \\_\\ \\|  |  /\\  \\___ |  |(  <_> )|   |  \\\\  ___/  \\___ \\ 
                    /_______  /|__|/____  > |__|   |__|   |__| |___  /|____/  \\___  >|__| \\____/ |___|  / \\___  >/____  >
                            \\/          \\/                         \\/             \\/                  \\/      \\/      \\/
                    """);
            System.out.println("""
                    ---------------------------------
                    Ingrese una opción:
                                    
                    |1| Binomial
                    |2| Binomial Negativa
                    |3| HiperGeométrica
                    |4| Poisson
                    |5| Exponencial
                    |6| Salir
                    ---------------------------------
                    """);
            switch (validarEntero()) {
                case -1 -> System.out.println("\nIngrese un valor numérico.");
                case 1 -> binomial();
                case 2 -> binomialNegativa();
                case 3 -> hipergeometrica();
                case 4 -> poisson();
                case 5 -> exponencial();
                case 6 -> {return;}
                default -> System.out.println("Ingrese una opción válida.");
            }
        }

    }

    /**
     * Metodo que valida si se ingreso bien la probabilidad para calcularla
     *
     * @return true si está bien, false si no
     */
    @Override
    public int validarProb() {
        while (true) {
            System.out.println("""
                    \nIngrese la opción que quiere calcular: 
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
