package br.com.db1.db1start.bancoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.db1start.bancoapi.entity.Cliente;
import br.com.db1.db1start.bancoapi.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente criar(String nome, String cpf, String telefone) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        return clienteRepository.save(cliente);
    }
	
	public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("CLiente não encontrado"));
    }
	
	public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }
	
	public void deletarTodos() {
    	clienteRepository.deleteAll();
    }
}
