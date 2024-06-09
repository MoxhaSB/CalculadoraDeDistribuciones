package Objects;

import java.util.Scanner;

public class BinomialNegativa extends HelperImpl {

    /**
     * Scanner para leer datos
     */
    private Scanner read = new Scanner(System.in);

    /**
     * CONSTRUCTOR
     */
    public BinomialNegativa() {
        binomialNegativa();
    }

    public void binomialNegativa() {
        int r = 0;
        String p = null;

        while (true) {
            System.out.println("""
                    \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        Distribución Binomial Negativa        
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    
                    X ~ BINOMIAL NEGATIVA ( r , p )
                    
                    |+| x : cantidad de intentos hasta conseguir "r" éxitos
                    |+| r : cantidad de éxitos a llegar
                    |+| p : probabilidad de éxito
                    
                     \nIngrese una opción: 
                    |1| Calcular una probabilidad
                    |2| Calcular Esperanza
                    |3| Calcular Varianza
                    |4| Salir
                    """);

            switch (validarEntero()) {
                case -1: {
                    System.out.println("\nIngrese un valor numérico.");
                    break;
                }
                case 1:
                    // calcular la probabilidad
                    calcularProbabilidad(r, p);
                    break;
                case 2:
                    // se calcula la esperanza
                    calcularEsperanza(r, p);
                    break;
                case 3:
                    // calcular la varianza
                    calcularVarianza(r, p);
                    break;
                case 4:
                    // se retorna para salir
                    return;
                default:
                    System.out.println("\nIngrese una opción válida.");
            }
        }
    }

    /**
     * Método que calcula la probabilidad según un "r" y "p" ingresados
     * @param r cantidad de éxitos para terminar el experimento
     * @param p probabilidad de éxito
     */
    public void calcularProbabilidad(int r, String p) {

        while (true) {
            System.out.println("""
                    \n------------------------------------
                       Calcular Probabilidad Binomial Negativa
                    ------------------------------------
                       
                         p(x) = C(x-1,r-1) * p^r * (1-p)^(x-r)
                                                        
                    Ingrese valor r: 
                    """);

            r = validarEntero();
            if(r == -1) {
                System.out.println("\nIngrese un valor numérico");
            }else if (r <= 0) {
                System.out.println("\n|*| \"r\" debe ser un número entero positivo");
            } else {
                System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                p = read.next();
                double pp = convertStringToDouble(p);

                if (pp < 0 || pp > 1) {
                    System.out.println("\n|*| \"p\" debe ser un número entre 0 y 1");
                } else {
                    double resultado = 0;

                    switch (validarProb()) {
                        case 1 -> {
                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x < ?): ");
                                int x = validarEntero();

                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");
                                } else if (x < r) {
                                    System.out.println("\nEl valor de \"x\" no puede ser menor al valor de \"r\"");
                                } else {
                                    resultado = 0;
                                    for (int i = r; i < x; i++) {
                                        resultado += probabilidad(i, r, pp);
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
                                int x = validarEntero();
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");
                                } else {
                                    resultado = 0;
                                    for (int i = r; i <= x; i++) {
                                        resultado += probabilidad(i, r, pp);
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
                                int x = validarEntero();
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");
                                } else {
                                    resultado = probabilidad(x, r, pp);
                                    System.out.println("\nLa probabilidad es : " + resultado);
                                    return;
                                }
                            }
                        }
                        case 4 -> {
                            while (true) {
                                System.out.println("\nIngrese el número a calcular (x > ?): ");
                                int x = validarEntero();
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico.");
                                } else {
                                    resultado = 0;
                                    for (int i = x + 1; i <= 100; i++) {
                                        resultado += probabilidad(i, r, pp);  // aquí se podría usar un límite mayor para x
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
                                int x = validarEntero();
                                if (x == -1) {
                                    System.out.println("\nIngrese un valor numérico positivo.");
                                } else {
                                    resultado = 0;
                                    for (int i = x; i <= 100; i++) {
                                        resultado += probabilidad(i, r, pp);  // aquí se podría usar un límite mayor para x
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
     * Método que calcula la esperanza de una distribución binomial negativa
     * @param r cantidad de éxitos del experimento
     * @param p probabilidad de éxito
     */
    public void calcularEsperanza(int r, String p) {
        while (true) {
            System.out.println("""
                    \n------------------------------------
                          Calcular Esperanza Binomial Negativa
                    ------------------------------------
                    
                                  E[X] = r / p
                                                            
                    Ingrese valor r: 
                    """);
            r = validarEntero();

            if (r <= 0) {
                System.out.println("\n|*| \"r\" debe ser un número entero positivo");
            } else {
                System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                p = read.next();
                double pp = convertStringToDouble(p);

                if (pp < 0 || pp > 1) {
                    System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                } else {
                    System.out.println("\nLa esperanza es : " + (r / pp));
                    break;
                }
            }
        }
    }

    /**
     * Método que calcula la varianza de la distribución binomial negativa
     * @param r cantidad de éxitos del experimento
     * @param p probabilidad de éxito
     */
    public void calcularVarianza(int r, String p) {
        while (true) {
            System.out.println("""
                    \n------------------------------------
                          Calcular Varianza Binomial Negativa
                    ------------------------------------
                    
                           VAR[X] = r * (1 - p) / p^2
                                                            
                    Ingrese valor r: 
                    """);
            r = validarEntero();

            if (r <= 0) {
                System.out.println("\n|*| \"r\" debe ser un número entero positivo");
            } else {
                System.out.println("Valor p (escribir como 0,0012 (con coma) ): ");
                p = read.next();
                double pp = convertStringToDouble(p);

                if (pp < 0 || pp > 1) {
                    System.out.println("\n|*| \"p\" debe ser un número NO negativo ni mayor a 1");
                } else {
                    System.out.println("\nLa varianza es : " + (r * (1 - pp) / (pp * pp)));
                    break;
                }
            }
        }
    }

    /**
     * Método que calcula la probabilidad binomial negativa dado un x, r y p
     * @param x valor a comparar
     * @param r éxitos requeridos
     * @param p probabilidad de éxito
     * @return la probabilidad calculada
     */
    public double probabilidad(int x, int r, double p) {
        return combinatoria(x - 1, r - 1).doubleValue() * Math.pow(p, r) * Math.pow(1 - p, x - r);
    }

}
