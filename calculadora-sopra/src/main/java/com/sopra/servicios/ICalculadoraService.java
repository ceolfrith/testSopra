package com.sopra.servicios;

import com.sopra.utilidades.Operacion;

import java.math.BigDecimal;

public interface ICalculadoraService {

    /**
     * Calcula rdos para una operación dada
     *
     * @param cifra1
     * @param cifra2
     * @param opParam 
     * @return rdo
     */
    double calcula(BigDecimal cifra1, BigDecimal cifra2, String opParam);
}
