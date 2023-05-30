package br.edu.ifs.academico.rest.dto;

import br.edu.ifs.academico.model.ProdutoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {
    private Long codigoProduto;
    private String nome;
    private String descricao;
    @JsonFormat(pattern = "0.00")
    private BigDecimal precoUnitario;
    private Boolean ativo;
}
