package com.luiz.devops.enums;

public enum OperacaoEnum {
    DEPOSITO("DEPOSITO"), SAQUE("SAQUE");

    private String operacao;

    OperacaoEnum(String operacao) {
        this.operacao = operacao;
    }

    @Override
    public String toString() {
        return operacao;
    }
}