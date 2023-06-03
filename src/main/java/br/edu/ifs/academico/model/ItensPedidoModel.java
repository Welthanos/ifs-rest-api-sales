package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="TB_ITENS_PEDIDOS")
public class ItensPedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_itens_pedido;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private PedidoModel pedidoModel;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private ProdutoModel produtoModel;
}
