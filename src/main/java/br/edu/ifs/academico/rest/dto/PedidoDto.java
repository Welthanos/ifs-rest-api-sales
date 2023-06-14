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
public class PedidoDto {
    private Long codigoPedido;
    @JsonFormat(pattern = "0.00")
    private BigDecimal valor;
    private Boolean status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora;
    private Long codigoCliente;
    private Long codigoVendedor;
}
