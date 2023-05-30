package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.AlunoModel;
import br.edu.ifs.academico.model.VendedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorModel,Long> {

}
