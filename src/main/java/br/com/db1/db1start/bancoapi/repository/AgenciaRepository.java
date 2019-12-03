package br.com.db1.db1start.bancoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.db1.db1start.bancoapi.entity.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

	List<Agencia> findByCidadeId(Long id);
}
