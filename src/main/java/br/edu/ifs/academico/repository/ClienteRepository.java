package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    List<ClienteModel> findByNomeContaining(String nome);
    Optional<ClienteModel> findByCpf(String cpf);
}
