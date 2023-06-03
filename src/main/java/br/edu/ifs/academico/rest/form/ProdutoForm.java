package br.edu.ifs.academico.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProdutoForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 128)
    private String nome;

    @NotEmpty
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 256)
    private String descricao;

    @NotEmpty
    @NotNull(message = "O preço do produto não pode ser nulo.")
    @JsonFormat(pattern = "0.00")
    private BigDecimal precoUnitario;

    @NotEmpty
    @NotNull(message = "O campo Ativo não pode estar nulo.")
    private Boolean ativo;
}
