package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ProdutoModel;
import br.edu.ifs.academico.rest.dto.ProdutoDto;
import br.edu.ifs.academico.repository.ProdutoRepository;
import br.edu.ifs.academico.rest.form.ProdutoForm;
import br.edu.ifs.academico.rest.form.ProdutoUpdateForm;
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
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoDto findById(long codigoProduto) {
        try {
            ProdutoModel produtoModel = produtoRepository.findById(codigoProduto).get();
            return convertProdutoModelToProdutoDto(produtoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoProduto + ", Tipo: " + ProdutoModel.class.getName());
        }
    }

    public List<ProdutoDto> findAll(){
        List<ProdutoModel> produtoList = produtoRepository.findAll();
        return convertListToDto(produtoList);
    }

    public ProdutoDto insert(ProdutoForm produtoForm) {
        try {
            ProdutoModel produtoNovo = convertProdutoFormToProdutoModel(produtoForm);
            Optional<ProdutoModel> byNome = produtoRepository.findByNome(produtoNovo.getNome());
            if (byNome.isPresent()) {
                throw new IllegalStateException("Nome do produto já registrado.");
            }
            produtoNovo = produtoRepository.save(produtoNovo);
            return convertProdutoModelToProdutoDto(produtoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Produto não foi(foram) preenchido(s).");
        }
    }

    public ProdutoDto update(ProdutoUpdateForm produtoUpdateForm, long codigoProduto) {
        try {
            Optional<ProdutoModel> produtoExistente = produtoRepository.findById(codigoProduto);
            if (produtoExistente.isPresent()) {
                ProdutoModel produtoAtualizado = produtoExistente.get();
                produtoAtualizado.setNome(produtoUpdateForm.getNome());
                produtoAtualizado.setDescricao(produtoUpdateForm.getDescricao());
                produtoAtualizado.setPrecoUnitario(produtoUpdateForm.getPrecoUnitario());
                produtoAtualizado.setAtivo(produtoUpdateForm.getAtivo());
                produtoRepository.save(produtoAtualizado);
                return convertProdutoModelToProdutoDto(produtoAtualizado);
            }else{
                throw new DataIntegrityException("O Produto não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Produto não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoProduto) {
        try {
            if (produtoRepository.existsById(codigoProduto)) {
                produtoRepository.deleteById(codigoProduto);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o(a) Produto!");
        }
    }

    private ProdutoModel convertProdutoFormToProdutoModel(ProdutoForm produtoForm) {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome(produtoForm.getNome());
        produtoModel.setDescricao(produtoForm.getDescricao());
        produtoModel.setPrecoUnitario(produtoForm.getPrecoUnitario());
        produtoModel.setAtivo(produtoForm.getAtivo());
        return produtoModel;
    }

    private ProdutoDto convertProdutoModelToProdutoDto(ProdutoModel produtoModel) {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setCodigoProduto(produtoModel.getCodigo());
        produtoDto.setNome(produtoModel.getNome());
        produtoDto.setDescricao(produtoModel.getDescricao());
        produtoDto.setPrecoUnitario(produtoModel.getPrecoUnitario());
        produtoDto.setAtivo(produtoModel.getAtivo());
        return produtoDto;
    }

    private List<ProdutoDto> convertListToDto(List<ProdutoModel> list){
        List<ProdutoDto> produtoDtoList = new ArrayList<>();
        for (ProdutoModel produtoModel : list) {
            ProdutoDto produtoDto = this.convertProdutoModelToProdutoDto(produtoModel);
            produtoDtoList.add(produtoDto);
        }
        return produtoDtoList;
    }
}
