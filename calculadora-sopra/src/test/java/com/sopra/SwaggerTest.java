package com.sopra;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest(classes = ConfigAplicacion.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SwaggerTest {

  private String ficheroSalida = "swagger.json";

  private File directorioSalida = new File("target");

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void execute() {
    try {
      MvcResult mvcResult =
          this.mockMvc.perform(get("/v3/api-docs").accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk()).andReturn();
      MockHttpServletResponse response = mvcResult.getResponse();
      String swaggerJson = response.getContentAsString();

      directorioSalida.mkdirs();
      Files.write(Paths.get(directorioSalida.getAbsolutePath() + "/" + ficheroSalida),
          swaggerJson.getBytes());

    } catch (Exception e) {
      System.out.println("Ha ocurrido un error" + e);
    }
  }

}
