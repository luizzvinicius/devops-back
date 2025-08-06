package com.luiz.devops.enums;

public enum OperacaoEnum {
    DEPOSITO("deposito"), SAQUE("saque");

    private String operacao;

    OperacaoEnum(String operacao) {
        this.operacao = operacao;
    }

    @Override
    public String toString() {
        return operacao;
    }
}