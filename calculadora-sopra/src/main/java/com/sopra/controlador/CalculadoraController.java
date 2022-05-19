package com.sopra.controlador;

import com.sopra.servicios.ICalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import io.corp.calculator.*;

/**
 * Gestión del método GET 
 */
@RestController
@RequestMapping("/api")
public class CalculadoraController {

    @Autowired
    private ICalculadoraService servicioCalculadora;

    private TracerImpl tracer = new TracerImpl();


    @GetMapping(value = "/calcula")
    public ResponseEntity<Double> calcula(@RequestParam(name = "cifra1") BigDecimal cifra1,
                                            @RequestParam(name = "cifra2") BigDecimal cifra2,
                                            @RequestParam(name = "operacion") String operacion) {

        double rdo = this.servicioCalculadora.calcula(cifra1, cifra2, operacion);
        tracer.trace(rdo);
        return new ResponseEntity<>(rdo, HttpStatus.OK);
    }
}
