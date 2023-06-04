package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="TB_PEDIDOS")
public class PedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "valor", columnDefinition = "numeric(18, 2)", nullable = false)
    private BigDecimal valor;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "dataHora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "codigoCliente", referencedColumnName = "codigo")
    private ClienteModel clienteModel;

    @ManyToOne
    @JoinColumn(name = "codigoVendedor", referencedColumnName = "codigo")
    private VendedorModel vendedorModel;
}