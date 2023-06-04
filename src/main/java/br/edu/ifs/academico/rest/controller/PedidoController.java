package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.PedidoDto;
import br.edu.ifs.academico.rest.form.PedidoForm;
import br.edu.ifs.academico.rest.form.PedidoUpdateForm;
import br.edu.ifs.academico.service.PedidoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll() {
        List<PedidoDto> pedidoDtoList = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> find(@PathVariable("id") long codigoPedido) {
        PedidoDto pedidoDto = pedidoService.findById(codigoPedido);
        return ResponseEntity.ok().body(pedidoDto);
    }

    @PostMapping
    public ResponseEntity<PedidoDto> insert(@Valid @RequestBody PedidoForm pedidoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        PedidoDto pedidoDto = pedidoService.insert(pedidoForm);
        return ResponseEntity.ok().body(pedidoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(@Valid @RequestBody PedidoUpdateForm pedidoUpdateForm
            , @PathVariable("id") long codigoPedido, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        PedidoDto pedidoDto = pedidoService.update(pedidoUpdateForm, codigoPedido);
        return ResponseEntity.ok().body(pedidoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoPedido) {
        pedidoService.delete(codigoPedido);
        return ResponseEntity.noContent().build();
    }
}

