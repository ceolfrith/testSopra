package com.sopra.servicios;

import com.sopra.utilidades.Operacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



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
    public double calcula(double cifra1, double cifra2, String opParam) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Calcular resultado para : {} {} {}", cifra1, cifra2, opParam);
        }

        Operacion operacion = Operacion.computa(opParam);

        if(operacion == null) {
        	LOGGER.error("Operación no soportada: {}", opParam);
            throw new RuntimeException("Operación no soportada: " + opParam);
        }

        switch (operacion) {
            case SUMA:
                return cifra1 + cifra2;
            case RESTA:
                return cifra1 - cifra2;
            default:
                if(LOGGER.isErrorEnabled()) {
                    LOGGER.error("No es posible realizar la operación seleccionada: {}", operacion);
                }
                throw new RuntimeException("No es posible realizar la operación seleccionada: " + operacion.toString());

        }
     }
        
}
