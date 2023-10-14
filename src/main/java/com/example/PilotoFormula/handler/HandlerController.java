package com.example.PilotoFormula.handler;

import com.example.PilotoFormula.exceptions.ErroNaoEncontrado;
import com.example.PilotoFormula.exceptions.ErroSolicitacao;
import com.example.PilotoFormula.exceptions.RespostasExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(ErroSolicitacao.class)
    public ResponseEntity<RespostasExceptions> handleErroSolicitacao(ErroSolicitacao ex) {
        return new ResponseEntity<>(
                RespostasExceptions.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception")
                        .detalhes(ex.getMessage())
                        .msgdev(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErroNaoEncontrado.class)
    public ResponseEntity<RespostasExceptions> handleErroNaoEncotrado(ErroNaoEncontrado ex) {
        return new ResponseEntity<>(
                RespostasExceptions.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Not Found Exception")
                        .detalhes(ex.getMessage())
                        .msgdev(ex.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);

    }

}