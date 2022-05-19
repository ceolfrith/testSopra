package com.sopra.servicios;

import com.sopra.utilidades.Operacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * Implementación de la interfaz 
 */
@Service
public class CalculadoraService implements ICalculadoraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraService.class);


    /**
     * @param cifra1
     * @param cifra2
     * @param opParam
     * @return
     */
    @Override
    public double calcula(BigDecimal cifra1, BigDecimal cifra2, String opParam) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Calcular rdo para : {} {} {}", cifra1, cifra2, opParam);
        }

        Operacion operacion = Operacion.computa(opParam);

        if(operacion == null) {
            throw new RuntimeException("No se ha podido realizar operación: " + opParam);
        }

        switch (operacion) {
            case SUMA:
                return cifra1.add(cifra2).doubleValue();
            case RESTA:
                return cifra1.subtract(cifra2).doubleValue();
            default:
                if(LOGGER.isErrorEnabled()) {
                    LOGGER.error("No es posible realizar la operación seleccionada: {}", operacion);
                }
                throw new RuntimeException("No es posible realizar la operación seleccionada: " + operacion.toString());

        }
    }
}
