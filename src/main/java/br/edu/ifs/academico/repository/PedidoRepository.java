package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
