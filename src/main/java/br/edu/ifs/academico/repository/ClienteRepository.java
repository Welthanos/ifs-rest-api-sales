package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.AlunoModel;
import br.edu.ifs.academico.model.ClienteModel;
import br.edu.ifs.academico.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
