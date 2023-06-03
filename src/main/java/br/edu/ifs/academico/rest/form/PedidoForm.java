package br.edu.ifs.academico.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoForm {

    @NotEmpty
    @NotNull(message = "O valor do pedido não pode ser nulo.")
    @JsonFormat(pattern = "0.00")
    private BigDecimal valor;

    @NotEmpty
    @NotNull(message = "O status do pedido não pode ser nulo.")
    private Boolean status;

    @NotEmpty
    @NotNull(message = "A data e o horário não pode ser nulo.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    @NotNull(message = "O campo de Cliente não pode ser nulo.")
    private Long codigoCliente;

    @NotNull(message = "O campo de Vendedor não pode ser nulo.")
    private Long codigoVendedor;
}
