package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long> {
    Optional<LoginModel> findByLogin(String login);
}
