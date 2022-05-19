package com.sopra.utilidades;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Operacion {
	
    SUMA("+"),
    RESTA("-");

    private static final Operacion[] valores = new Operacion[]{SUMA, RESTA};

    private String signo;

    Operacion(String signo) {
        this.signo = signo;
    }

    private String getSigno() {
        return this.signo;
    }

    /**
     * @param valor
     * @return
     */
    @JsonCreator
    public static Operacion computa(String valor) {

        for (int i = 0; i < valores.length; ++i) {
            Operacion opEfectiva = valores[i];
            if (valor.equalsIgnoreCase(opEfectiva.name()) ||
                    valor.equalsIgnoreCase(opEfectiva.getSigno())) {
                return opEfectiva;
            }
        }

        throw new RuntimeException("Operación no computable: " + valor);
    }
}
