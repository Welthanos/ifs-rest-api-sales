package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_VENDEDORES")
public class VendedorModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vendedor;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}
