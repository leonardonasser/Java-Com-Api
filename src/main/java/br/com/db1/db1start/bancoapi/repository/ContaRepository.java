package br.com.db1.db1start.bancoapi.repository;

import br.com.db1.db1start.bancoapi.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
