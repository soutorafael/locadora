package br.com.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.locadora.entity.Cliente;

@Repository
public interface RepositoryCliente extends JpaRepository<Cliente, Long> {

}
