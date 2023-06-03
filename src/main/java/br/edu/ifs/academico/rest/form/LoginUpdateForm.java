package br.edu.ifs.academico.rest.form;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class LoginUpdateForm {

    @NotEmpty
    @NotBlank(message = "A Senha não pode estar em branco.")
    @Size(max = 256)
    private String senha;

    @NotEmpty
    @NotNull(message = "O campo Ativo não pode estar nulo.")
    private Boolean ativo;
}
