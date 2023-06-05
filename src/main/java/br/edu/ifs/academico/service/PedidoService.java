package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ClienteModel;
import br.edu.ifs.academico.model.VendedorModel;
import br.edu.ifs.academico.repository.VendedorRepository;
import br.edu.ifs.academico.repository.ClienteRepository;
import br.edu.ifs.academico.rest.dto.PedidoDto;
import br.edu.ifs.academico.model.PedidoModel;
import br.edu.ifs.academico.repository.PedidoRepository;
import br.edu.ifs.academico.rest.form.PedidoForm;
import br.edu.ifs.academico.rest.form.PedidoUpdateForm;
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
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VendedorRepository vendedorRepository;

    public PedidoDto findById(long codigoPedido) {
        try {
            PedidoModel pedidoModel = pedidoRepository.findById(codigoPedido).get();
            return convertPedidoModelToPedidoDto(pedidoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoPedido + ", Tipo: " + PedidoModel.class.getName());
        }
    }

    public List<PedidoDto> findAll(){
        List<PedidoModel> pedidoList = pedidoRepository.findAll();
        return convertListToDto(pedidoList);
    }

    public PedidoDto insert(PedidoForm pedidoForm) {
        try {
            PedidoModel pedidoNovo = convertPedidoFormToPedidoModel(pedidoForm);
            pedidoNovo = pedidoRepository.save(pedidoNovo);
            return convertPedidoModelToPedidoDto(pedidoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Pedido não foi(foram) preenchido(s).");
        }
    }

    public PedidoDto update(PedidoUpdateForm pedidoUpdateForm, long codigoPedido) {
        try {
            Optional<PedidoModel> pedidoExistente = pedidoRepository.findById(codigoPedido);
            if (pedidoExistente.isPresent()) {
                PedidoModel pedidoAtualizado = pedidoExistente.get();
                pedidoAtualizado.setValor(pedidoUpdateForm.getValor());
                pedidoAtualizado.setStatus(pedidoUpdateForm.getStatus());
                pedidoRepository.save(pedidoAtualizado);
                return convertPedidoModelToPedidoDto(pedidoAtualizado);
            }else{
                throw new DataIntegrityException("O Código do Pedido não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Pedido não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoPedido) {
        try {
            if (pedidoRepository.existsById(codigoPedido)) {
                pedidoRepository.deleteById(codigoPedido);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o Pedido!");
        }
    }

    private PedidoModel convertPedidoFormToPedidoModel(PedidoForm pedidoForm) {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setValor(pedidoForm.getValor());
        pedidoModel.setStatus(pedidoForm.getStatus());
        pedidoModel.setDataHora(pedidoForm.getDataHora());
        // Código do Vendedor
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setCodigo(pedidoForm.getCodigoCliente());
        pedidoModel.setClienteModel(clienteModel);
        // Código do Vendedor
        VendedorModel vendedorModel = new VendedorModel();
        vendedorModel.setCodigo(pedidoForm.getCodigoCliente());
        pedidoModel.setVendedorModel(vendedorModel);
        return pedidoModel;
    }

    private PedidoDto convertPedidoModelToPedidoDto(PedidoModel pedidoModel) {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setCodigoPedido(pedidoModel.getCodigo());
        pedidoDto.setValor(pedidoModel.getValor());
        pedidoDto.setStatus(pedidoModel.getStatus());
        pedidoDto.setDataHora(pedidoModel.getDataHora());
        return pedidoDto;
    }

    private List<PedidoDto> convertListToDto(List<PedidoModel> list){
        List<PedidoDto> pedidoDtoList = new ArrayList<>();
        for (PedidoModel pedidoModel : list) {
            PedidoDto pedidoDto = this.convertPedidoModelToPedidoDto(pedidoModel);
            pedidoDtoList.add(pedidoDto);
        }
        return pedidoDtoList;
    }
}

