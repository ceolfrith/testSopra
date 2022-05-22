package com.sopra.servicios;


public interface ICalculadoraService {

    /**
     * Calcula rdos para una operación dada
     *
     * @param cifra1
     * @param cifra2
     * @param operacion 
     * @return rdo
     */
	double calcula(double cifra1, double cifra2, String operacion);
}
