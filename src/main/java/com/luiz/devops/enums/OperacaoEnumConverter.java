package com.luiz.devops.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.luiz.devops.exceptions.InvalidEnumException;

@Converter(autoApply = true)
public class OperacaoEnumConverter implements AttributeConverter<OperacaoEnum, String> {
    @Override
    public String convertToDatabaseColumn(OperacaoEnum operacaoEnum) {
        return operacaoEnum.toString();
    }

    @Override
    public OperacaoEnum convertToEntityAttribute(String s) {
        return switch (s) {
            case "DEPOSITO" -> OperacaoEnum.DEPOSITO;
            case "SAQUE" -> OperacaoEnum.SAQUE;
            default -> throw new InvalidEnumException(s);
        };
    }
}