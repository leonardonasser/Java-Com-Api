package br.com.db1.db1start.bancoapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.db1.db1start.bancoapi.entity.Cidade;
import br.com.db1.db1start.bancoapi.entity.Estado;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CidadeServiceTest {
	
	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private EstadoService estadoService;
	
	private Estado estadoDefault;
	
	@BeforeAll
	public void criar_estado_default() {
		this.estadoDefault = estadoService.criar("Paraná");
	}
	
	@BeforeEach
	public void deletar_registros_antes_de_cada_teste() {
		cidadeService.deletarTodos();
	}
	
	@AfterAll
    public void deletar_registros_depois_dos_testes() {
		cidadeService.deletarTodos();
		estadoService.deletarTodos();
	}
	
	@Test
    public void deve_criar_uma_cidade() {
        String nomeCidade = "Maringá";
        Cidade cidade = cidadeService.criar(nomeCidade, estadoDefault.getId());
        Assertions.assertEquals(cidade.getNome(), nomeCidade);
    }
	
	@Test
    public void deve_atualizar_o_nome_de_uma_cidade() {
    	Cidade cidade = cidadeService.criar("Maaaaringá", estadoDefault.getId());
        String novoNome = "Maringá";
        Cidade cidadeAtualizada = cidadeService.atualizar(cidade.getId(), novoNome);
        Assertions.assertEquals(cidadeAtualizada.getNome(), novoNome);
    }
    
    @Test
    public void deve_buscar_uma_cidade_pelo_id() {
    	Cidade cidade = cidadeService.criar("Maringá", estadoDefault.getId());
        Cidade cidadeBuscada = cidadeService.buscarPorId(cidade.getId());
        Assertions.assertEquals(cidadeBuscada.getId(), cidade.getId());
        Assertions.assertEquals(cidadeBuscada.getNome(), "Maringá");
    }
    
    @Test
    public void deve_buscar_todas_as_cidades_de_um_estado() {
    	Estado outroEstado = estadoService.criar("São Paulo");
    	cidadeService.criar("Maringá", estadoDefault.getId());
    	cidadeService.criar("Londrina", estadoDefault.getId());
    	cidadeService.criar("Curitiba", estadoDefault.getId());
    	cidadeService.criar("Campo Mourão", estadoDefault.getId());
    	cidadeService.criar("Campinas", outroEstado.getId());
        
    	List<Cidade> todasAsCidadesDoEstado1 = 
    			cidadeService.buscarTodosPeloIdDoEstado(estadoDefault.getId());
        List<String> nomesCidadesEstado1 = 
        		todasAsCidadesDoEstado1.stream().map(Cidade::getNome).collect(Collectors.toList());
        Assertions.assertEquals(todasAsCidadesDoEstado1.size(), 4);
        Assertions.assertTrue(nomesCidadesEstado1.contains("Maringá"));
        Assertions.assertTrue(nomesCidadesEstado1.contains("Londrina"));
        Assertions.assertTrue(nomesCidadesEstado1.contains("Curitiba"));
        Assertions.assertTrue(nomesCidadesEstado1.contains("Campo Mourão"));
        Assertions.assertFalse(nomesCidadesEstado1.contains("Campinas"));
    }
    
    @Test
    public void deve_buscar_todas_as_cidades_cadastradas() {
    	cidadeService.criar("Maringá", estadoDefault.getId());
    	cidadeService.criar("Sarandi", estadoDefault.getId());
    	cidadeService.criar("Marialva", estadoDefault.getId());
    	cidadeService.criar("Pato Branco", estadoDefault.getId());
    	cidadeService.criar("Curitiba", estadoDefault.getId());
    	cidadeService.criar("Londrina", estadoDefault.getId());
    	
        List<Cidade> todasAsCidades = cidadeService.buscarTodos();
        List<String> nomesCidades = 
        		todasAsCidades.stream().map(Cidade::getNome).collect(Collectors.toList());
        Assertions.assertEquals(todasAsCidades.size(), 6);
        Assertions.assertTrue(nomesCidades.contains("Maringá"));
        Assertions.assertTrue(nomesCidades.contains("Sarandi"));
        Assertions.assertTrue(nomesCidades.contains("Marialva"));
        Assertions.assertTrue(nomesCidades.contains("Pato Branco"));
        Assertions.assertTrue(nomesCidades.contains("Curitiba"));
        Assertions.assertTrue(nomesCidades.contains("Londrina"));
    }
    
    @Test
    public void deve_deletar_uma_cidade_por_id() {
    	Cidade cidadeCriada = cidadeService.criar("Maringá", estadoDefault.getId());
        cidadeService.deletarPorId(cidadeCriada.getId());
        
        Assertions.assertThrows(RuntimeException.class, () -> {
        	cidadeService.buscarPorId(cidadeCriada.getId());
        });
    }

}
