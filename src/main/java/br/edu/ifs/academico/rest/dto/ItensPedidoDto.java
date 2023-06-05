package br.edu.ifs.academico.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDto {
    private Long codigoItensPedido;
    private Integer quantidade;
    private Long codigoPedido;
    private Long codigoProduto;
}
