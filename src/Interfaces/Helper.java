package Interfaces;

import java.math.BigInteger;

public interface Helper {

    /**
     * Metodo que valida si se ingreso bien la probabilidad para calcularla
     *
     * @return true si está bien, false si no
     */
    int validarProb();

    /**
     * Metodo que calcula una combinatoria con BigInteger (numero demasiado grandes)
     * @param n valor de arriba de combinatoria
     * @param k valor de abajo de combinatoria
     * @return el valor de la combinatoria
     */
    BigInteger combinatoria(int n, int k);

    /**
     * Metodo que calcula el valor de un factorial con  BigInteger
     * @param numero numero a calcular el factorial
     * @return el factorial
     */
    BigInteger factorial(int numero);

    /**
     * Metodo que verifica si un texto ingresado contiene letras o no
     * @return el numero ingresado o -1
     */
    int validarEntero();


}
