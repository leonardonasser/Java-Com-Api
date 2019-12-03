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

import br.com.db1.db1start.bancoapi.entity.Agencia;
import br.com.db1.db1start.bancoapi.entity.Cidade;
import br.com.db1.db1start.bancoapi.entity.Estado;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AgenciaServiceTest {
	
	@Autowired
	private AgenciaService agenciaService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	private Estado estadoDefault;
	private Cidade cidadeDefault;
	
	@BeforeAll
	public void criar_estado_e_cidade_default() {
		this.estadoDefault = estadoService.criar("Paraná");
        this.cidadeDefault = cidadeService.criar("Maringá", estadoDefault.getId());
	}
	
	@BeforeEach
    public void deletar_registros_antes_de_cada_teste() {
		agenciaService.deletarTodos();
	}
	
	@AfterAll
    public void deletar_registros_depois_dos_testes() {
		agenciaService.deletarTodos();
		cidadeService.deletarTodos();
		estadoService.deletarTodos();
	}
	
	@Test
    public void deve_criar_uma_agencia() {
        String numero = "444";
        String numeroBanco = "987";
        Agencia agencia = agenciaService.criar(numero, numeroBanco, cidadeDefault.getId());
        Assertions.assertEquals(agencia.getNumero(), numero);
        Assertions.assertEquals(agencia.getNumeroBanco(), numeroBanco);
        Assertions.assertEquals(agencia.getCidade().getId(), cidadeDefault.getId());
    }
	
	@Test
    public void deve_buscar_todas_as_agencias_de_uma_cidade() {
    	Cidade outraCidade = cidadeService.criar("Londrina", estadoDefault.getId());
    	Agencia ag1 = agenciaService.criar("111", "222", cidadeDefault.getId());
    	Agencia ag2 = agenciaService.criar("222", "333", cidadeDefault.getId());
    	Agencia ag3 = agenciaService.criar("333", "444", cidadeDefault.getId());
    	Agencia ag4 = agenciaService.criar("444", "555", outraCidade.getId());
        
    	List<Agencia> todasAsAgenciasDaCidadeDefault = 
    			agenciaService.buscarTodasPeloIdDaCidade(cidadeDefault.getId());
        List<Long> idsAgenciasCidade = 
        		todasAsAgenciasDaCidadeDefault.stream().map(Agencia::getId).collect(Collectors.toList());
        Assertions.assertEquals(todasAsAgenciasDaCidadeDefault.size(), 3);
        Assertions.assertTrue(idsAgenciasCidade.contains(ag1.getId()));
        Assertions.assertTrue(idsAgenciasCidade.contains(ag2.getId()));
        Assertions.assertTrue(idsAgenciasCidade.contains(ag3.getId()));
        Assertions.assertFalse(idsAgenciasCidade.contains(ag4.getId()));
    }

}
