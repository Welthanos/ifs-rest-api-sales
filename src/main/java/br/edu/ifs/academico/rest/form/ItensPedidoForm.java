package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ItensPedidoForm {

    @NotEmpty
    @NotNull(message = "A quantidade não pode ser nula.")
    private Integer quantidade;

    @NotNull(message = "O campo de Cliente não pode ser nulo.")
    private Long codigoPedido;

    @NotNull(message = "O campo de Vendedor não pode ser nulo.")
    private Long codigoProduto;
}