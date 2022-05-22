package com.sopra;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigAplicacion.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCalculadora {
    
	@LocalServerPort
    int randomServerPort;
	
	private static final String OPERACION_SUMA = "suma";
	private static final String OPERACION_RESTA = "resta";

    /**
     * Se invoca un get -> localhost:(puerto)/api/calcula 
     * @param cifra1
     * @param cifra2
     * @param operacion
     * @return
     * @throws URISyntaxException
     */
    private ResponseEntity<Double> calcula(Object cifra1, Object cifra2, String opParam) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort +
                "/api/calcula?cifra1=" + cifra1 +
                "&cifra2=" + cifra2 + "&operacion=" + opParam;
        URI uri = new URI(baseUrl);

        ResponseEntity<Double> resultado = restTemplate.getForEntity(uri, Double.class);
        return resultado;
    }

    @Test
    public void testSumaOK() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(8.75, 2.50, OPERACION_SUMA);

        //Rdo
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(11.25d, resultado.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testRestaResultadoPositivoOK() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(1500, 600, OPERACION_RESTA);

        //Rdo
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(900.0d, resultado.getBody().doubleValue(), 0.001d);
    }
    
    @Test
    public void testRestaResultadoNegativoOK() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(2, 8, OPERACION_RESTA);

        //Rdo
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(-6.0d, resultado.getBody().doubleValue(), 0.001d);
    }
    
    @Test(expected = BadRequest.class)
    public void testSumaBadRequestKO() throws URISyntaxException {

       	calcula("X", 2, OPERACION_SUMA);
    }
    
    @Test(expected = BadRequest.class)
    public void testRestaBadRequestKO() throws URISyntaxException {

       	calcula(4, "YY", OPERACION_RESTA);
    }
    
    @Test(expected = BadRequest.class)
    public void testOperacionMalBadRequestKO() throws URISyntaxException {

       	calcula(3.5, 1, "División");
    }
    
    @Test(expected = BadRequest.class)
    public void testNullParametrosBadRequestKO() throws URISyntaxException {

       	calcula(null, null, OPERACION_RESTA);
    }

}