package br.edu.ifs.academico.rest.dto;

import br.edu.ifs.academico.model.LoginModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private Long codigoLogin;
    private String login;
    private String senha;
    private Boolean ativo;
}
