package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.ClienteModel;
import br.edu.ifs.academico.model.VendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel,Long> {

    //Usando o operador LIKE
    List<ClienteModel> findByNomeContaining(String nome);

    //Usando a cláusula ORDER BY DESC
    List<ClienteModel> findByOrderByNomeDesc();

    //Buscando através do CPF
    Optional<VendedorModel> findByCpf(String cpf);
}
