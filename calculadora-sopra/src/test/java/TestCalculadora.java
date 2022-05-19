import com.sopra.ConfigAplicacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigAplicacion.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCalculadora {
    @LocalServerPort
    int randomServerPort;

    /**
     * Se invoca un get -> localhost:(puerto)/api/calcula 
     * @param cifra1
     * @param cifra2
     * @param opParam
     * @return
     * @throws URISyntaxException
     */
    private ResponseEntity<Double> calcula(double cifra1, double cifra2, String opParam) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort +
                "/api/calcula?cifra1=" + cifra1 +
                "&cifra2=" + cifra2 + "&opParam=" + opParam;
        URI uri = new URI(baseUrl);

        ResponseEntity<Double> resultado = restTemplate.getForEntity(uri, Double.class);
        return resultado;
    }

    @Test
    public void testSumaOK() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(8, 2, "suma");

        //Rdo
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(10.0d, resultado.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testRestaOK() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(8, 2, "resta");

        //Rdo
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(-2.0d, resultado.getBody().doubleValue(), 0.001d);
    }

}