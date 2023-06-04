package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.ProdutoDto;
import br.edu.ifs.academico.rest.form.ProdutoForm;
import br.edu.ifs.academico.rest.form.ProdutoUpdateForm;
import br.edu.ifs.academico.service.ProdutoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll() {
        List<ProdutoDto> produtoDtoList = produtoService.findAll();
        return ResponseEntity.ok().body(produtoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> find(@PathVariable("id") long codigoProduto) {
        ProdutoDto produtoDto = produtoService.findById(codigoProduto);
        return ResponseEntity.ok().body(produtoDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> insert(@Valid @RequestBody ProdutoForm produtoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProdutoDto produtoDto = produtoService.insert(produtoForm);
        return ResponseEntity.ok().body(produtoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@Valid @RequestBody ProdutoUpdateForm produtoUpdateForm
            , @PathVariable("id") long codigoProduto, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProdutoDto produtoDto = produtoService.update(produtoUpdateForm, codigoProduto);
        return ResponseEntity.ok().body(produtoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoProduto) {
        produtoService.delete(codigoProduto);
        return ResponseEntity.noContent().build();
    }
}
