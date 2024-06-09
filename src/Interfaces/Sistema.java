import java.math.BigInteger;

public interface Sistema {
    /**
     * Metodo que contiene y entrega valores con la distribución binomial
     */
    void binomial();

    /**
     * Metodo que contiene y entrega valores con la distribución binomial negativa
     */
    void binomialNegativa();

    /**
     * Metodo que contiene y entrega valores con la distribución hipergeometrica
     */
    void hipergeometrica();

    /**
     * Metodo que contiene y entrega valores con la distribución Poisson
     */
    void poisson();

    /**
     * Metodo que contiene y entrega valores con la distribución exponencial
     */
    void exponencial();

    /**
     * Metodo que contiene y lleva a las calculadoras de distribucioens
     */
    void menuPrincipal();

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
