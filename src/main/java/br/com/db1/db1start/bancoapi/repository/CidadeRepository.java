package br.com.db1.db1start.bancoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.db1.db1start.bancoapi.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	List<Cidade> findByEstadoId(Long id);
}
