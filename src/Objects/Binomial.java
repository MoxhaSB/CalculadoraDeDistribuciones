package Objects;

import java.util.Scanner;

/**
 * Clase Binomial, contiene los cálculos de una distribución binomial
 */
public class Binomial extends HelperImpl{

    /**
     * Scanner para leer datos
     */
    private Scanner read = new Scanner(System.in);

    /**
     * CONSTRUCTOR
     */
    public Binomial(){
        binomial();
    }

    /**
     * Método que contiene los cálculos de la distribución binomial
     */
    public void binomial() {
        while (true) {
            System.out.println("""
                    \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                            Distribución Binomial        
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    
                    X ~ BINOMIAL ( n , p )
                    
                    |+| x : número de éxitos en "n" ensayos
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
                    //calcular la probabilidad
                    calcularProbabilidad();
                    break;
                case 2:
                    //calcular la esperanza
                    calcularEsperanza();
                    break;
                case 3:
                    //calcular la varianza
                    calcularVarianza();
                    break;
                case 4:
                    //salir
                    return;
                default:
                    System.out.println("\nIngrese una opción válida.");
            }
        }
    }

    /**
     * Método que calcula la probabilidad según un "n" y "p" ingresados
     */
    public void calcularProbabilidad(){
        while(true) {
            System.out.println("""
                                \n------------------------------------
                                   Calcular Probabilidad Binomial
                                ------------------------------------
                                   
                                     P(X = x) = C(n, x) * p^x * (1-p)^(n-x)
                                                            
                                Ingrese valor n: 
                                """);

            //se lee el dato
            int n = validarEntero();

            //si es menor a 0 (no puede ser negativo)
            if (n < 0) {
                System.out.println("\n|*| \"n\"  debe ser un número entero NO negativo");

            } else {

                //se pide el valor de la probabilidad
                System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                String pStr = read.next();//se lee como String
                double p = convertStringToDouble(pStr); //se transforma a Double

                //el valor de p tiene que estar entre 0 y 1
                if (p < 0 || p > 1) {
                    System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                } else {

                    double resultado;
                    //se pide la la probabilidad a calcular
                    switch (validarProb()) {
                        //CASO X<?
                        case 1 -> {

                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x < ?): ");
                                int x = validarEntero();

                                //si ingresó un valor No numérico
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");

                                    //si ingresó un valor negativo
                                } else if (x < 0) {
                                    System.out.println("\nEl valor de \"x\" no puede ser negativo");

                                    //si x es mayor a n (por problemas de la combinatoria)
                                } else if (x > n) {
                                    System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                    //si está bien, recién se calcula la probabilidad
                                } else {
                                    resultado = 0;
                                    for (int i = 0; i < x; i++) {
                                        resultado += probabilidad(n, i, p);
                                    }
                                    System.out.println("\nLa probabilidad es: " + resultado);
                                    break;
                                }
                            }
                            return;
                        }
                        //CASO X<=?
                        case 2 -> {

                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x <= ?): ");
                                int x = validarEntero();

                                //si se ingresó un valor NO numérico
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");

                                    //si se ingresó un valor negativo
                                } else if (x < 0) {
                                    System.out.println("\nEl valor de \"x\" no puede ser negativo");

                                    //si x es mayor a n (problemas de combinatoria)
                                } else if (x > n) {
                                    System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                    //si está bien, recién se calcula la probabilidad
                                } else {
                                    resultado = 0;
                                    for (int i = 0; i <= x; i++) {
                                        resultado += probabilidad(n, i, p);
                                    }
                                    System.out.println("\nLa probabilidad es: " + resultado);
                                    break;
                                }
                            }
                            return;
                        }
                        //CASO X = ?
                        case 3 -> {
                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x = ?): ");
                                int x = validarEntero();

                                //si se ingresó un valor NO numérico
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");

                                    //si se ingresó un valor negativo
                                } else if (x < 0) {
                                    System.out.println("\nEl valor de \"x\" no puede ser negativo");

                                    //si x es mayor a n (problemas de combinatoria)
                                } else if (x > n) {
                                    System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                    //si está bien, recién se calcula la probabilidad
                                } else {
                                    resultado = probabilidad(n, x, p);
                                    System.out.println("\nLa probabilidad es: " + resultado);
                                    return;
                                }
                            }
                        }
                        //CASO X > ?
                        case 4 -> {

                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x > ?): ");
                                int x = validarEntero();

                                //si se ingresó un valor NO numérico
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");

                                    //si se ingresó un valor negativo
                                } else if (x < 0) {
                                    System.out.println("\nEl valor de \"x\" no puede ser negativo");

                                    //si x es mayor a n (problemas de combinatoria)
                                } else if (x > n) {
                                    System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                    //si está bien, recién se calcula la probabilidad
                                } else {
                                    resultado = 0;
                                    for (int i = x + 1; i <= n; i++) {
                                        resultado += probabilidad(n, i, p);
                                    }
                                    System.out.println("\nLa probabilidad es: " + resultado);
                                    break;
                                }
                            }
                            return;
                        }
                        //CASO X >= ?
                        case 5 -> {

                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x >= ?): ");
                                int x = validarEntero();

                                //si se ingresó un valor NO numérico
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");

                                    //si se ingresó un valor negativo
                                } else if (x < 0) {
                                    System.out.println("\nEl valor de \"x\" no puede ser negativo");

                                    //si x es mayor a n (problemas con combinatoria)
                                } else if (x > n) {
                                    System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                    //si está bien, recién se calcula la probabilidad
                                } else {
                                    resultado = 0;
                                    for (int i = x; i <= n; i++) {
                                        resultado += probabilidad(n, i, p);
                                    }
                                    System.out.println("\nLa probabilidad es: " + resultado);
                                    break;
                                }
                            }
                            return;
                        }
                        //CASO -1 para salir
                        case -1 -> {
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Método que calcula la esperanza de una distribución binomial
     */
    public void calcularEsperanza(){
        while(true) {
            System.out.println("""
                                \n------------------------------------
                                      Calcular Esperanza Binomial
                                ------------------------------------
                                
                                            E[X] = n * p
                                                            
                                Ingrese valor n: 
                                """);
            int n = validarEntero();

            if(n ==-1) {
                System.out.println("\nIngrese un valor numérico.");

                //si se ingresó un valor negativo
            }else if (n < 0) {
                System.out.println("\n|*| \"n\"  debe ser un número positivo");

            } else {
                System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                String pStr = read.next();//se guarda en string
                double p = convertStringToDouble(pStr); //se trasnforma a double (con formato decimal(comas))

                if (p < 0 || p > 1) {
                    System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                } else {
                    System.out.println("\nLa esperanza es: " + n * p);
                    break;
                }
            }
        }
    }

    /**
     * Método que calcula la varianza de la distribución binomial
     */
    public void calcularVarianza(){
        while(true) {
            System.out.println("""
                                \n------------------------------------
                                      Calcular Varianza Binomial
                                ------------------------------------
                                
                                      VAR[X] = n * p * (1 - p)
                                                            
                                Ingrese valor n: 
                                """);
            int n = validarEntero();
            if(n ==-1) {
                System.out.println("\nIngrese un valor numérico.");

                //si se ingresó un valor negativo
            }else if (n < 0) {
                System.out.println("\n|*| \"n\"  debe ser un número positivo");

            } else {
                System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                String pStr = read.next();
                double p = convertStringToDouble(pStr);

                if (p < 0 || p > 1) {
                    System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                } else {
                    System.out.println("\nLa varianza es: " + (n * p * (1 - p)));
                    break;
                }
            }
        }
    }

    /**
     * Método que calcula la probabilidad binomial
     * @param n cantidad de intentos
     * @param x cantidad a comparar -> x<1 x>=6
     * @param p probabilidad
     * @return la probabilidad calculada
     */
    public double probabilidad(int n, int x, double p){
        return combinatoria(n, x).doubleValue() * Math.pow(p, x) * Math.pow(1 - p, n - x);
    }

}

