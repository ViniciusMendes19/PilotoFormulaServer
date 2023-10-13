package com.example.PilotoFormula.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErroNaoEncontrado extends RuntimeException {
    public ErroNaoEncontrado(String mensagem){
        super(mensagem);
    }
}

