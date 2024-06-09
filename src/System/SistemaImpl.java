package System;

import Interfaces.Sistema;
import Objects.*;


import java.util.Scanner;
import java.util.function.BinaryOperator;

public class SistemaImpl implements Sistema {

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
                case 1 -> new Binomial();
                case 2 -> new BinomialNegativa();
                case 3 -> new Hipergeometrica();
                case 4 -> new Poisson();
                case 5 -> new Exponencial();
                case 6 -> {return;}
                default -> System.out.println("Ingrese una opción válida.");
            }
        }

    }

    /**
     * Metodo que verifica si el texto ingresado contiene letras o no
     * @return -1 si tiene letras, el texto si sólo son números
     */
    private int validarEntero() {
        return new HelperImpl().validarEntero();
    }


}
