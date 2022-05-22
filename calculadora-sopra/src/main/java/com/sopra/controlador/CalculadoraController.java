package com.sopra.controlador;

import com.sopra.servicios.ICalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.corp.calculator.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Gestión del método GET 
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Calculadora")
public class CalculadoraController {

    @Autowired
    private ICalculadoraService servicioCalculadora;

    private TracerImpl tracer = new TracerImpl();
    
    @Operation(summary = "Servicio calculadora",
    		description = "Operaciones de suma y resta de dos operandos",
    		parameters =  {
    				@Parameter(in = ParameterIn.QUERY, name = "cifra1", required = true, description = "primer operando", 
    						example = "5.1", schema = @Schema(type = "number")),
    				@Parameter(in = ParameterIn.QUERY, name = "cifra2", required = true, description = "segundo operando", 
					example = "6.5", schema = @Schema(type = "number")),
    				@Parameter(in = ParameterIn.QUERY, name = "operacion", required = true, description = "tipo de operacion. Valores permitidos: suma y resta", 
					example = "suma", schema = @Schema(type = "string")),
    		})
    @ApiResponses(value = {@ApiResponse(description = "Resultado de la operacion", responseCode = "200"),
    		@ApiResponse(description = "Invocacion erronea", responseCode = "400", content = @Content)})
    
    @GetMapping(value = "/calcula")
    public ResponseEntity<Double> calcula(@RequestParam(name = "cifra1") double cifra1,
                                          @RequestParam(name = "cifra2") double cifra2,
                                          @RequestParam(name = "operacion") String operacion) {
    
    	Double resultado = this.servicioCalculadora.calcula(cifra1, cifra2, operacion);
   		tracer.trace(resultado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
