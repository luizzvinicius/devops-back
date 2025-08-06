package com.luiz.devops.exceptions;

public class OperacaoInvalidaException extends RuntimeException {
    public OperacaoInvalidaException() {
        super("Operação inválida");
    }
}