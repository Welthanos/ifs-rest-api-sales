package br.edu.ifs.academico.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PedidoUpdateForm {

    @NotEmpty
    @NotNull(message = "O valor do pedido não pode ser nulo.")
    @JsonFormat(pattern = "0.00")
    private BigDecimal valor;

    @NotEmpty
    @NotNull(message = "O status do pedido não pode ser nulo.")
    private Boolean status;
}
