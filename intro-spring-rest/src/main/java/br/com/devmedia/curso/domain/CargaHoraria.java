package br.com.devmedia.curso.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CargaHoraria {

    VINTE_HORAS("20HS"),
    TRINTA_HORAS("30HS"),
    QUARENTA_HORAS("40HS"),
    SESSENTA_HORAS("60HS");

    private String horas;

    CargaHoraria(String horas) {
        this.horas = horas;
    }

    @JsonValue  //Para receber as solitações com o valor e não com o nome da constante, ex 20HS ao invés de VINTE_HORAS
    public String getHoras() {
        return horas;
    }
}
