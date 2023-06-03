package br.edu.ifs.academico.rest.dto;

import br.edu.ifs.academico.model.ClienteModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long idCliente;
    private String nome;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Boolean ativo;
}
