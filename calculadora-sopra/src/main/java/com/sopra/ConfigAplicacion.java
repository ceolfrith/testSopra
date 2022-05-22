package com.sopra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@ComponentScan(basePackages = "com.sopra")
@EnableAutoConfiguration
@SpringBootApplication
public class ConfigAplicacion {

  
    /**
     * Entrada / Inicio del programa
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigAplicacion.class, args);
    }
}
