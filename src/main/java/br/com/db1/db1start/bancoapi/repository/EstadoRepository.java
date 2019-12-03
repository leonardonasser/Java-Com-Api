package br.com.db1.db1start.bancoapi.repository;

import br.com.db1.db1start.bancoapi.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByNome(String nome);

}
