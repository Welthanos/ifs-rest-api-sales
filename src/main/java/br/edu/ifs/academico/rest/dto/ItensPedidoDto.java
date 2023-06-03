package br.edu.ifs.academico.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDto {
    private Long codigoItensPedido;
    private Integer quantidade;
    private Long codigoPedido;
    private Long codigoProduto;
}
