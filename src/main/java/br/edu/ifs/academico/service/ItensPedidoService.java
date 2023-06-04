package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.PedidoModel;
import br.edu.ifs.academico.model.ProdutoModel;
import br.edu.ifs.academico.repository.ProdutoRepository;
import br.edu.ifs.academico.repository.PedidoRepository;
import br.edu.ifs.academico.rest.dto.ItensPedidoDto;
import br.edu.ifs.academico.model.ItensPedidoModel;
import br.edu.ifs.academico.repository.ItensPedidoRepository;
import br.edu.ifs.academico.rest.form.ItensPedidoForm;
import br.edu.ifs.academico.rest.form.ItensPedidoUpdateForm;
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
public class ItensPedidoService {

    @Autowired
    ItensPedidoRepository itensPedidoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public ItensPedidoDto findById(long codigoItensPedido) {
        try {
            ItensPedidoModel itensPedidoModel = itensPedidoRepository.findById(codigoItensPedido).get();
            return convertItensPedidoModelToItensPedidoDto(itensPedidoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoItensPedido + ", Tipo: " + ItensPedidoModel.class.getName());
        }
    }

    public List<ItensPedidoDto> findAll(){
        List<ItensPedidoModel> itensPedidoList = itensPedidoRepository.findAll();
        return convertListToDto(itensPedidoList);
    }

    public ItensPedidoDto insert(ItensPedidoForm itensPedidoForm) {
        try {
            ItensPedidoModel itensPedidoNovo = convertItensPedidoFormToItensPedidoModel(itensPedidoForm);
            itensPedidoNovo = itensPedidoRepository.save(itensPedidoNovo);
            return convertItensPedidoModelToItensPedidoDto(itensPedidoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do(s) Item(ns) do Pedido não foi(foram) preenchido(s).");
        }
    }

    public ItensPedidoDto update(ItensPedidoUpdateForm itensPedidoUpdateForm, long codigoItensPedido) {
        try {
            Optional<ItensPedidoModel> itensPedidoExistente = itensPedidoRepository.findById(codigoItensPedido);
            if (itensPedidoExistente.isPresent()) {
                ItensPedidoModel itensPedidoAtualizado = itensPedidoExistente.get();
                itensPedidoAtualizado.setQuantidade(itensPedidoUpdateForm.getQuantidade());
                itensPedidoRepository.save(itensPedidoAtualizado);
                return convertItensPedidoModelToItensPedidoDto(itensPedidoAtualizado);
            }else{
                throw new DataIntegrityException("O Código do(s) Item(ns) do Pedido não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do(s) Item(ns) do Pedido não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoItensPedido) {
        try {
            if (itensPedidoRepository.existsById(codigoItensPedido)) {
                itensPedidoRepository.deleteById(codigoItensPedido);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um ItensPedido!");
        }
    }

    private ItensPedidoModel convertItensPedidoFormToItensPedidoModel(ItensPedidoForm itensPedidoForm) {
        ItensPedidoModel itensPedidoModel = new ItensPedidoModel();
        itensPedidoModel.setQuantidade(itensPedidoForm.getQuantidade());

        // Código do Pedido
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setCodigo(itensPedidoForm.getCodigoPedido());
        itensPedidoModel.setPedidoModel(pedidoModel);

        // Código do Produto
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setCodigo(itensPedidoForm.getCodigoPedido());
        itensPedidoModel.setProdutoModel(produtoModel);
        return itensPedidoModel;
    }

    private ItensPedidoDto convertItensPedidoModelToItensPedidoDto(ItensPedidoModel itensPedidoModel) {
        ItensPedidoDto itensPedidoDto = new ItensPedidoDto();
        itensPedidoDto.setCodigoItensPedido(itensPedidoModel.getCodigo());
        itensPedidoDto.setQuantidade(itensPedidoModel.getQuantidade());
        return itensPedidoDto;
    }

    private List<ItensPedidoDto> convertListToDto(List<ItensPedidoModel> list){
        List<ItensPedidoDto> itensPedidoDtoList = new ArrayList<>();
        for (ItensPedidoModel itensPedidoModel : list) {
            ItensPedidoDto itensPedidoDto = this.convertItensPedidoModelToItensPedidoDto(itensPedidoModel);
            itensPedidoDtoList.add(itensPedidoDto);
        }
        return itensPedidoDtoList;
    }
}

