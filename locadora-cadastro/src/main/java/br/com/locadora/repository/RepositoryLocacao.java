package br.com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.locadora.entity.Locacao;

public interface RepositoryLocacao extends JpaRepository<Locacao, Long>{
	
	@Query( value =  "SELECT * FROM Locacao  where ID_CLIENTE = ?1 and IS_PENDENTE = 1", nativeQuery = true)
	List<Locacao> listLocacoesPendentes(Long idCliente);

}
