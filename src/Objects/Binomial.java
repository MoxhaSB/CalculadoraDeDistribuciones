package Objects;

import java.util.Scanner;

/**
 * Clase Binomial, contiene los calculos de una distribución binomial
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
     * Metodo que contiene los cálculos de la distribucion binomial
     */
    public void binomial() {
        int n = 0;
        String p = null;

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
                    //calcular la probabilidad
                    calcularProbabilidad(n,p);
                    break;
                case 2:
                    //se calcula la esperanza
                   calcularEsperanza(n,p);
                    break;

                case 3:
                    //calcualr la varianza
                    calcularVarianza(n,p);
                    break;

                case 4:
                //se retorna para salir
                {return;}

                default:
                    System.out.println("\nIngrese una opción válida.");
            }

        }
    }

    /**
     * Metodd que calcula la probabilidad según un "n" y "p" ingresados
     * @param n cantidad de repeticiones del experimento
     * @param p probabilidad de éxito del intento
     */
    public void calcularProbabilidad(int n, String p){
        while(true) {
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
                                } else if (numero < 0) {
                                    System.out.println("\nEl valor de \"x\" no puede ser negativo");
                                } else if (numero > n) {
                                    System.out.println("\nEl valor de \"x\" no puede ser mayor al valor de \"n\"");

                                    //cualquier otro caso
                                } else {
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
                                    if (resultado == 0) {
                                        System.out.println("\nLa probabilidad es 0");
                                    } else {
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
                    break;
                }
            }
        }
    }

    /**
     * Método que calcula la esperanza de una distribución binomial
     * @param n cantidad de repeticiones del experimento
     * @param p probabilidad de éxito del intento
     */
    public void calcularEsperanza(int n,String p){
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
    }

    /**
     * Método que calcula la varianza de la distribución binomial
     * @param n cantidad de repeticiones del experimento
     * @param p probabilidad de éxito del intento
     */
    public void calcularVarianza(int n, String p){
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
    }

}
