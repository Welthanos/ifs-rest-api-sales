package br.edu.ifs.academico.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PedidoUpdateForm {

    @NotNull(message = "O valor do pedido não pode ser nulo.")
    @JsonFormat(pattern = "0.00")
    private BigDecimal valor;

    @NotNull(message = "O status do pedido não pode ser nulo.")
    private Boolean status;
}
