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

import br.com.db1.db1start.bancoapi.entity.Estado;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class EstadoServiceTest {

	@Autowired
	private EstadoService estadoService;

	@BeforeEach
	public void deletar_registros_antes_de_cada_teste() {
		estadoService.deletarTodos();
	}
	
	@AfterAll
	public void deletar_registros_depois_dos_testes() {
		estadoService.deletarTodos();
	}

	@Test
	public void deve_criar_um_estado() {
		String nomeEstado = "Acre";
		Estado estado = estadoService.criar(nomeEstado);
		Assertions.assertEquals(estado.getNome(), nomeEstado);
	}

	@Test
	public void deve_atualizar_o_nome_de_um_estado() {
		Estado estado = estadoService.criar("Paaaraná");
		String novoNome = "Paraná";
		Estado estadoAtualizado = estadoService.atualizar(estado.getId(), novoNome);
		Assertions.assertEquals(estadoAtualizado.getNome(), novoNome);
	}

	@Test
	public void deve_buscar_um_estado_pelo_id() {
		Estado estado = estadoService.criar("Amazonas");
		Estado estadoBuscado = estadoService.buscarPorId(estado.getId());
		Assertions.assertEquals(estadoBuscado.getId(), estado.getId());
		Assertions.assertEquals(estadoBuscado.getNome(), "Amazonas");
	}

	@Test
	public void deve_buscar_todos_os_estados_cadastrados() {
		estadoService.criar("Amazonas");
		estadoService.criar("Paraná");
		estadoService.criar("São Paulo");
		estadoService.criar("Rio de Janeiro");
		estadoService.criar("Minas Gerais");
		List<Estado> todosOsEstados = estadoService.buscarTodos();
		List<String> nomesEstados = 
				todosOsEstados.stream().map(Estado::getNome).collect(Collectors.toList());
		Assertions.assertEquals(todosOsEstados.size(), 5);
		Assertions.assertTrue(nomesEstados.contains("Amazonas"));
		Assertions.assertTrue(nomesEstados.contains("Paraná"));
		Assertions.assertTrue(nomesEstados.contains("São Paulo"));
		Assertions.assertTrue(nomesEstados.contains("Rio de Janeiro"));
		Assertions.assertTrue(nomesEstados.contains("Minas Gerais"));
	}

	@Test
	public void deve_deletar_um_estado_por_id() {
		Estado estadoCriado = estadoService.criar("Amazonas");
		estadoService.deletarPorId(estadoCriado.getId());

		Assertions.assertThrows(RuntimeException.class, () -> {
			estadoService.buscarPorId(estadoCriado.getId());
		});
	}

}
