package com.sopra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.sopra")
@EnableAutoConfiguration
public class ConfigAplicacion extends SpringBootServletInitializer {

    /**
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ConfigAplicacion.class);
    }


    /**
     * Entrada / Inicio del programa
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigAplicacion.class, args);
    }
}
