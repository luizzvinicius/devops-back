package com.luiz.devops.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException(String entity) {
        super(entity + " NOT FOUND");
    }
}