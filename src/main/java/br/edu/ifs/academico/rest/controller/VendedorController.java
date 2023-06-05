package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.VendedorDto;
import br.edu.ifs.academico.rest.form.VendedorForm;
import br.edu.ifs.academico.rest.form.VendedorUpdateForm;
import br.edu.ifs.academico.service.VendedorService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<VendedorDto>> findAll() {
        List<VendedorDto> vendedorDtoList = vendedorService.findAll();
        return ResponseEntity.ok().body(vendedorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDto> find(@PathVariable("id") long codigoVendedor) {
        VendedorDto vendedorDto = vendedorService.findById(codigoVendedor);
        return ResponseEntity.ok().body(vendedorDto);
    }

    @PostMapping
    public ResponseEntity<VendedorDto> insert(@Valid @RequestBody VendedorForm vendedorForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        VendedorDto vendedorDto = vendedorService.insert(vendedorForm);
        return ResponseEntity.ok().body(vendedorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDto> update(@Valid @RequestBody VendedorUpdateForm vendedorUpdateForm
            , @PathVariable("id") long codigoVendedor, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        VendedorDto vendedorDto = vendedorService.update(vendedorUpdateForm, codigoVendedor);
        return ResponseEntity.ok().body(vendedorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoVendedor) {
        vendedorService.delete(codigoVendedor);
        return ResponseEntity.noContent().build();
    }
}
