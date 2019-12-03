package br.com.db1.db1start.bancoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.db1.db1start.bancoapi.entity.Cliente;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ClienteServiceTest {

	@Autowired
	private ClienteService clienteService;
	
	@BeforeEach
    public void deletar_registros_antes_de_cada_teste() {
		clienteService.deletarTodos();
	}
	
	@AfterAll
    public void deletar_registros_depois_dos_testes() {
		clienteService.deletarTodos();
	}
	
	@Test
    public void deve_criar_um_cliente() {
        String cpf = "012.345.678-90";
        String nome = "Ana";
        String telefone = "44 3030-3030";
        Cliente cliente = clienteService.criar(nome, cpf, telefone);
        Assertions.assertEquals(cliente.getNome(), nome);
        Assertions.assertEquals(cliente.getCpf(), cpf);
        Assertions.assertEquals(cliente.getTelefone(), telefone);
    }
	
	@Test
    public void deve_buscar_todos_os_clientes() {
		Cliente cliente1 = clienteService.criar("João", "012.345.678-90", "44 3030-3030");
		Cliente cliente2 = clienteService.criar("Ana", "012.345.678-90", "44 3030-3030");
		Cliente cliente3 = clienteService.criar("Maria", "012.345.678-90", "44 3030-3030");
		Cliente cliente4 = clienteService.criar("Cláudio", "012.345.678-90", "44 3030-3030");
        
    	List<Cliente> todosOsClientes = 
    			clienteService.buscarTodos();
        List<Long> idsClientes = 
        		todosOsClientes.stream().map(Cliente::getId).collect(Collectors.toList());
        Assertions.assertEquals(todosOsClientes.size(), 4);
        Assertions.assertTrue(idsClientes.contains(cliente1.getId()));
        Assertions.assertTrue(idsClientes.contains(cliente2.getId()));
        Assertions.assertTrue(idsClientes.contains(cliente3.getId()));
        Assertions.assertTrue(idsClientes.contains(cliente4.getId()));
    }
}
