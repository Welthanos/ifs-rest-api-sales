package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.VendedorModel;
import br.edu.ifs.academico.rest.dto.VendedorDto;
import br.edu.ifs.academico.repository.VendedorRepository;
import br.edu.ifs.academico.rest.form.VendedorForm;
import br.edu.ifs.academico.rest.form.VendedorUpdateForm;
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
public class VendedorService {

    @Autowired
    VendedorRepository vendedorRepository;

    public VendedorDto findById(long codigoVendedor) {
        try {
            VendedorModel vendedorModel = vendedorRepository.findById(codigoVendedor).get();
            return convertVendedorModelToVendedorDto(vendedorModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoVendedor + ", Tipo: " + VendedorModel.class.getName());
        }
    }

    public List<VendedorDto> findAll(){
        List<VendedorModel> vendedorList = vendedorRepository.findAll();
        return convertListToDto(vendedorList);
    }

    public VendedorDto insert(VendedorForm vendedorForm) {
        try {
            VendedorModel vendedorNovo = convertVendedorFormToVendedorModel(vendedorForm);
            Optional<VendedorModel> byCpf = vendedorRepository.findByCpf(vendedorNovo.getCpf());
            if (byCpf.isPresent()) {
                throw new IllegalStateException("CPF já registrado.");
            }
            vendedorNovo = vendedorRepository.save(vendedorNovo);
            return convertVendedorModelToVendedorDto(vendedorNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do(a) Vendedor não foi(foram) preenchido(s).");
        }
    }

    public VendedorDto update(VendedorUpdateForm vendedorUpdateForm, long codigoVendedor) {
        try {
            Optional<VendedorModel> vendedorExistente = vendedorRepository.findById(codigoVendedor);
            if (vendedorExistente.isPresent()) {
                VendedorModel vendedorAtualizado = vendedorExistente.get();
                vendedorAtualizado.setNome(vendedorUpdateForm.getNome());
                vendedorAtualizado.setAtivo(vendedorUpdateForm.getAtivo());
                vendedorRepository.save(vendedorAtualizado);
                return convertVendedorModelToVendedorDto(vendedorAtualizado);
            }else{
                throw new DataIntegrityException("A Código do Vendedor não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do(a) Vendedor não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoVendedor) {
        try {
            if (vendedorRepository.existsById(codigoVendedor)) {
                vendedorRepository.deleteById(codigoVendedor);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o(a) Vendedor!");
        }
    }

    private VendedorModel convertVendedorFormToVendedorModel(VendedorForm vendedorForm) {
        VendedorModel vendedorModel = new VendedorModel();
        vendedorModel.setNome(vendedorForm.getNome());
        vendedorModel.setCpf(vendedorForm.getCpf());
        vendedorModel.setDataNascimento(vendedorForm.getDataNascimento());
        vendedorModel.setAtivo(vendedorForm.getAtivo());
        return vendedorModel;
    }

    private VendedorDto convertVendedorModelToVendedorDto(VendedorModel vendedorModel) {
        VendedorDto vendedorDto = new VendedorDto();
        vendedorDto.setCodigoVendedor(vendedorModel.getCodigo());
        vendedorDto.setNome(vendedorModel.getNome());
        vendedorDto.setCpf(vendedorModel.getCpf());
        vendedorDto.setDataNascimento(vendedorModel.getDataNascimento());
        vendedorDto.setAtivo(vendedorModel.getAtivo());
        return vendedorDto;
    }

    private List<VendedorDto> convertListToDto(List<VendedorModel> list){
        List<VendedorDto> vendedorDtoList = new ArrayList<>();
        for (VendedorModel vendedorModel : list) {
            VendedorDto vendedorDto = this.convertVendedorModelToVendedorDto(vendedorModel);
            vendedorDtoList.add(vendedorDto);
        }
        return vendedorDtoList;
    }
}
