package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClienteUpdateForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 256)
    private String nome;
}
