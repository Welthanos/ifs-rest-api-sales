package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_LOGIN")
public class LoginModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_login;

    @Column(name = "login", length = 256, nullable = false, unique = true)
    private String login;

    @Column(name = "senha", length = 256, nullable = false)
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;
}
