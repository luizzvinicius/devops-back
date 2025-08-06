package teste.fiesc.conta.dtos.pessoa;

import java.util.List;

import com.luiz.devops.models.Conta;

public record PessoaResponseDto(
    Long id,
    String nome,
    String cpf,
    String endereco
    ,List<Conta> conta
) {   
}