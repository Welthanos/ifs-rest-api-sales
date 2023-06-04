package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.ClienteModel;
import br.edu.ifs.academico.rest.dto.ClienteDto;
import br.edu.ifs.academico.repository.ClienteRepository;
import br.edu.ifs.academico.rest.form.ClienteForm;
import br.edu.ifs.academico.rest.form.ClienteUpdateForm;
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
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteDto findById(long codigoCliente) {
        try {
            ClienteModel clienteModel = clienteRepository.findById(codigoCliente).get();
            return convertClienteModelToClienteDto(clienteModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoCliente + ", Tipo: " + ClienteModel.class.getName());
        }
    }

    public List<ClienteDto> findAll(){
        List<ClienteModel> clienteList = clienteRepository.findAll();
        return convertListToDto(clienteList);
    }

    public ClienteDto insert(ClienteForm clienteForm) {
        try {
            ClienteModel clienteNovo = convertClienteFormToClienteModel(clienteForm);
            Optional<ClienteModel> byCpf = clienteRepository.findByCpf(clienteNovo.getCpf());
            if (byCpf.isPresent()) {
                throw new IllegalStateException("CPF já registrado.");
            }
            clienteNovo = clienteRepository.save(clienteNovo);
            return convertClienteModelToClienteDto(clienteNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do(a) Cliente não foi(foram) preenchido(s).");
        }
    }

    public ClienteDto update(ClienteUpdateForm clienteUpdateForm, long codigoCliente) {
        try {
            Optional<ClienteModel> clienteExistente = clienteRepository.findById(codigoCliente);
            if (clienteExistente.isPresent()) {
                ClienteModel clienteAtualizado = clienteExistente.get();
                clienteAtualizado.setNome(clienteUpdateForm.getNome());
                clienteAtualizado.setAtivo(clienteUpdateForm.getAtivo());
                clienteRepository.save(clienteAtualizado);
                return convertClienteModelToClienteDto(clienteAtualizado);
            }else{
                throw new DataIntegrityException("A Código do Cliente não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do(a) Cliente não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoCliente) {
        try {
            if (clienteRepository.existsById(codigoCliente)) {
                clienteRepository.deleteById(codigoCliente);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o(a) Cliente!");
        }
    }

    private ClienteModel convertClienteFormToClienteModel(ClienteForm clienteForm) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setNome(clienteForm.getNome());
        clienteModel.setCpf(clienteForm.getCpf());
        clienteModel.setDataNascimento(clienteForm.getDataNascimento());
        clienteModel.setAtivo(clienteForm.getAtivo());
        return clienteModel;
    }

    private ClienteDto convertClienteModelToClienteDto(ClienteModel clienteModel) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCodigoCliente(clienteModel.getCodigo());
        clienteDto.setNome(clienteModel.getNome());
        clienteDto.setCpf(clienteModel.getCpf());
        clienteDto.setDataNascimento(clienteModel.getDataNascimento());
        clienteDto.setAtivo(clienteModel.getAtivo());
        return clienteDto;
    }

    private List<ClienteDto> convertListToDto(List<ClienteModel> list){
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        for (ClienteModel clienteModel : list) {
            ClienteDto clienteDto = this.convertClienteModelToClienteDto(clienteModel);
            clienteDtoList.add(clienteDto);
        }
        return clienteDtoList;
    }
}
