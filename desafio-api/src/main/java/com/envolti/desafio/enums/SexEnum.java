package com.envolti.desafio.enums;

import lombok.Getter;

public enum SexEnum {
	F("FEMININO"),
    M("MASCULINO"),
    O("OUTROS");
    
	@Getter
    private String sexo;

    private SexEnum(String sexo) {
        this.sexo = sexo;
    }    
}