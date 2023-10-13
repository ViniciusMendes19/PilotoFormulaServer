package com.example.PilotoFormula.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroSolicitacao extends RuntimeException {

    public ErroSolicitacao(String mensagem){
        super(mensagem);
    }
}
