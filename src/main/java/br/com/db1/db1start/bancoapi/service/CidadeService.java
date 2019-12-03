package br.com.db1.db1start.bancoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.db1start.bancoapi.entity.Cidade;
import br.com.db1.db1start.bancoapi.entity.Estado;
import br.com.db1.db1start.bancoapi.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoService estadoService;

	public Cidade criar(String nome, Long estadoId) {
		Estado estado = estadoService.buscarPorId(estadoId);
		Cidade cidade = new Cidade();
		cidade.setNome(nome);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public Cidade atualizar(Long cidadeId, String novoNome) {
        Cidade cidade = buscarPorId(cidadeId);
        cidade.setNome(novoNome);
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarPorId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    }
    
    public List<Cidade> buscarTodosPeloIdDoEstado(Long estadoId) {
        return cidadeRepository.findByEstadoId(estadoId);
    }

    public List<Cidade> buscarTodos() {
        return cidadeRepository.findAll();
    }

    public void deletarPorId(Long id) {
        cidadeRepository.deleteById(id);
    }
	
	public void deletarTodos() {
    	cidadeRepository.deleteAll();
    }
}
