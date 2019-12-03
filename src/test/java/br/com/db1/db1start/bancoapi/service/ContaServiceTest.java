package br.com.db1.db1start.bancoapi.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.db1.db1start.bancoapi.entity.Agencia;
import br.com.db1.db1start.bancoapi.entity.Cidade;
import br.com.db1.db1start.bancoapi.entity.Cliente;
import br.com.db1.db1start.bancoapi.entity.Conta;
import br.com.db1.db1start.bancoapi.entity.Estado;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContaServiceTest {

	@Autowired
	private ContaService contaService;
	@Autowired
	private AgenciaService agenciaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	private Cliente clienteDefault;
	private Agencia agenciaDefault;
	private Estado estadoDefault;
	private Cidade cidadeDefault;
	
	@BeforeAll
	public void criar_agencia_estado_e_cidade_default() {
		this.estadoDefault = estadoService.criar("Paraná");
        this.cidadeDefault = cidadeService.criar("Maringá", estadoDefault.getId());
        this.agenciaDefault = agenciaService.criar("123", "1212121", cidadeDefault.getId());
        this.clienteDefault = clienteService.criar("Mariana", "012.345.678-90", "3030-3030");
	}
	
	@BeforeEach
    public void deletar_registros_antes_de_cada_teste() {
		contaService.deletarTodos();
	}
	
	@AfterAll
    public void deletar_registros_depois_dos_testes() {
		contaService.deletarTodos();
		agenciaService.deletarTodos();
		cidadeService.deletarTodos();
		estadoService.deletarTodos();
	}
	
	@Test
    public void deve_criar_uma_conta() {
        Double saldo = 100.00;
        Conta conta = contaService.criar(saldo, agenciaDefault.getId(), clienteDefault.getId());
        Assertions.assertEquals(conta.getSaldo(), saldo);
        Assertions.assertEquals(conta.getAgencia().getId(), agenciaDefault.getId());
        Assertions.assertEquals(conta.getCliente().getId(), clienteDefault.getId());
    }
}
