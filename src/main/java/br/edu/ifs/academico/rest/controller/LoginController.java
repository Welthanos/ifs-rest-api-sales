package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.LoginDto;
import br.edu.ifs.academico.rest.form.LoginForm;
import br.edu.ifs.academico.rest.form.LoginUpdateForm;
import br.edu.ifs.academico.service.LoginService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<List<LoginDto>> findAll() {
        List<LoginDto> loginDtoList = loginService.findAll();
        return ResponseEntity.ok().body(loginDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginDto> find(@PathVariable("id") long codigoLogin) {
        LoginDto loginDto = loginService.findById(codigoLogin);
        return ResponseEntity.ok().body(loginDto);
    }

    @PostMapping
    public ResponseEntity<LoginDto> insert(@Valid @RequestBody LoginForm loginForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LoginDto loginDto = loginService.insert(loginForm);
        return ResponseEntity.ok().body(loginDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoginDto> update(@Valid @RequestBody LoginUpdateForm loginUpdateForm
            , @PathVariable("id") long codigoLogin, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        LoginDto loginDto = loginService.update(loginUpdateForm, codigoLogin);
        return ResponseEntity.ok().body(loginDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoLogin) {
        loginService.delete(codigoLogin);
        return ResponseEntity.noContent().build();
    }
}
