package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.LoginModel;
import br.edu.ifs.academico.rest.dto.LoginDto;
import br.edu.ifs.academico.repository.LoginRepository;
import br.edu.ifs.academico.rest.form.LoginForm;
import br.edu.ifs.academico.rest.form.LoginUpdateForm;
import br.edu.ifs.academico.service.exceptions.DataIntegrityException;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public LoginDto findById(long codigoLogin) {
        try {
            LoginModel loginModel = loginRepository.findById(codigoLogin).get();
            return convertLoginModelToLoginDto(loginModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoLogin + ", Tipo: " + LoginModel.class.getName());
        }
    }

    public List<LoginDto> findAll(){
        List<LoginModel> loginList = loginRepository.findAll();
        return convertListToDto(loginList);
    }

    public LoginDto insert(LoginForm loginForm) {
        try {
            LoginModel loginNovo = convertLoginFormToLoginModel(loginForm);
            Optional<LoginModel> byLogin = loginRepository.findByLogin(loginNovo.getLogin());
            if (byLogin.isPresent()) {
                throw new IllegalStateException("Login já registrado.");
            }
            loginNovo = loginRepository.save(loginNovo);
            return convertLoginModelToLoginDto(loginNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Login não foi(foram) preenchido(s).");
        }
    }

    public LoginDto update(LoginUpdateForm loginUpdateForm, long codigoLogin) {
        try {
            Optional<LoginModel> loginExistente = loginRepository.findById(codigoLogin);
            if (loginExistente.isPresent()) {
                LoginModel loginAtualizado = loginExistente.get();
                loginAtualizado.setSenha(loginUpdateForm.getSenha());
                loginAtualizado.setAtivo(loginUpdateForm.getAtivo());
                loginRepository.save(loginAtualizado);
                return convertLoginModelToLoginDto(loginAtualizado);
            }else{
                throw new DataIntegrityException("A Código do Login não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Login não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoLogin) {
        try {
            if (loginRepository.existsById(codigoLogin)) {
                loginRepository.deleteById(codigoLogin);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o Login!");
        }
    }

    private LoginModel convertLoginFormToLoginModel(LoginForm loginForm) {
        LoginModel loginModel = new LoginModel();
        loginModel.setLogin(loginForm.getLogin());
        loginModel.setSenha(loginForm.getSenha());
        loginModel.setAtivo(loginForm.getAtivo());
        return loginModel;
    }

    private LoginDto convertLoginModelToLoginDto(LoginModel loginModel) {
        LoginDto loginDto = new LoginDto();
        loginDto.setCodigoLogin(loginModel.getCodigo());
        loginDto.setLogin(loginModel.getLogin());
        loginDto.setSenha(loginModel.getSenha());
        loginDto.setAtivo(loginModel.getAtivo());
        return loginDto;
    }

    private List<LoginDto> convertListToDto(List<LoginModel> list){
        List<LoginDto> loginDtoList = new ArrayList<>();
        for (LoginModel loginModel : list) {
            LoginDto loginDto = this.convertLoginModelToLoginDto(loginModel);
            loginDtoList.add(loginDto);
        }
        return loginDtoList;
    }
}

