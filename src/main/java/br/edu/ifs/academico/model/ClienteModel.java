package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_CLIENTES")
public class ClienteModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate data_nascimento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}
