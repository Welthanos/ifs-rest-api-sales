package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="TB_ITENS_PEDIDOS")
public class ItensPedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "codigoPedido", referencedColumnName = "codigo")
    private PedidoModel pedidoModel;

    @ManyToOne
    @JoinColumn(name = "codigoProduto", referencedColumnName = "codigo")
    private ProdutoModel produtoModel;
}
