package com.example.PilotoFormula.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespostasExceptions {

    private String titulo;
    private int status;
    private String detalhes;
    private String msgdev;
}
