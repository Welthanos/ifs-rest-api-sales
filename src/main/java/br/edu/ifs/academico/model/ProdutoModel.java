package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_PRODUTOS")
public class ProdutoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;

    @Column(name = "nome", length = 128, nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", length = 256, nullable = false)
    private String descricao;

    @Column(name = "preco_unitario", columnDefinition = "numeric(18, 2)", nullable = false)
    private BigDecimal preco_unitario;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}

