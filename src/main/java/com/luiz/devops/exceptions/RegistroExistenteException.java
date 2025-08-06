package com.luiz.devops.exceptions;

public class RegistroExistenteException extends RuntimeException {
    public RegistroExistenteException(String registro, String entity) {
        super("Registro " + registro + " já cadastrado em " + entity + "!");
    }
}