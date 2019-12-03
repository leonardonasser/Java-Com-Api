package br.com.db1.db1start.bancoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.db1start.bancoapi.entity.Agencia;
import br.com.db1.db1start.bancoapi.entity.Cidade;
import br.com.db1.db1start.bancoapi.repository.AgenciaRepository;

@Service
public class AgenciaService {
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	@Autowired
	private CidadeService cidadeService;
	
	public Agencia criar(String numero, String numeroBanco, Long cidadeId) {
		Cidade cidade = cidadeService.buscarPorId(cidadeId);
		Agencia agencia = new Agencia();
		agencia.setCidade(cidade);
		agencia.setNumero(numero);
		agencia.setNumeroBanco(numeroBanco);
		return agenciaRepository.save(agencia);
	}
	
	public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id).orElseThrow(() -> new RuntimeException("Agência não encontrada"));
    }
	
	public List<Agencia> buscarTodasPeloIdDaCidade(Long cidadeId) {
        return agenciaRepository.findByCidadeId(cidadeId);
    }
	
	public void deletarTodos() {
    	agenciaRepository.deleteAll();
    }
	
}
