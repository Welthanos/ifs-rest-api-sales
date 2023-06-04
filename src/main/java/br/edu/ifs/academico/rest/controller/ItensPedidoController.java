package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ItensPedidoDto;
import br.edu.ifs.academico.rest.form.ItensPedidoForm;
import br.edu.ifs.academico.rest.form.ItensPedidoUpdateForm;
import br.edu.ifs.academico.service.ItensPedidoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/itens-pedidos")
public class ItensPedidoController {

    @Autowired
    private ItensPedidoService itensPedidoService;

    @GetMapping
    public ResponseEntity<List<ItensPedidoDto>> findAll() {
        List<ItensPedidoDto> itensPedidoDtoList = itensPedidoService.findAll();
        return ResponseEntity.ok().body(itensPedidoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensPedidoDto> find(@PathVariable("id") long codigoItensPedido) {
        ItensPedidoDto itensPedidoDto = itensPedidoService.findById(codigoItensPedido);
        return ResponseEntity.ok().body(itensPedidoDto);
    }

    @PostMapping
    public ResponseEntity<ItensPedidoDto> insert(@Valid @RequestBody ItensPedidoForm itensPedidoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ItensPedidoDto itensPedidoDto = itensPedidoService.insert(itensPedidoForm);
        return ResponseEntity.ok().body(itensPedidoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensPedidoDto> update(@Valid @RequestBody ItensPedidoUpdateForm itensPedidoUpdateForm
            , @PathVariable("id") long codigoItensPedido, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ItensPedidoDto itensPedidoDto = itensPedidoService.update(itensPedidoUpdateForm, codigoItensPedido);
        return ResponseEntity.ok().body(itensPedidoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoItensPedido) {
        itensPedidoService.delete(codigoItensPedido);
        return ResponseEntity.noContent().build();
    }
}