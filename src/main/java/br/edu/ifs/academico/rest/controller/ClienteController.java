package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ClienteDto;
import br.edu.ifs.academico.rest.form.ClienteForm;
import br.edu.ifs.academico.rest.form.ClienteUpdateForm;
import br.edu.ifs.academico.service.ClienteService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        List<ClienteDto> clienteDtoList = clienteService.findAll();
        return ResponseEntity.ok().body(clienteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> find(@PathVariable("id") long codigoCliente) {
        ClienteDto clienteDto = clienteService.findById(codigoCliente);
        return ResponseEntity.ok().body(clienteDto);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> insert(@Valid @RequestBody ClienteForm clienteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClienteDto clienteDto = clienteService.insert(clienteForm);
        return ResponseEntity.ok().body(clienteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@Valid @RequestBody ClienteUpdateForm clienteUpdateForm
            , @PathVariable("id") long codigoCliente, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClienteDto clienteDto = clienteService.update(clienteUpdateForm, codigoCliente);
        return ResponseEntity.ok().body(clienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoCliente) {
        clienteService.delete(codigoCliente);
        return ResponseEntity.noContent().build();
    }
}
