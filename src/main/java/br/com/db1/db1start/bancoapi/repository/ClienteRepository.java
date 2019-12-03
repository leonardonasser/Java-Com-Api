package br.com.db1.db1start.bancoapi.repository;

import br.com.db1.db1start.bancoapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
