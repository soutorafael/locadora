package br.com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.locadora.entity.LocacaoItens;

@Repository
public interface RepositoryLocacaoItem extends JpaRepository<LocacaoItens, Long>{
	
	@Query( value = "select * from locacaoitens where ID_LOCACAO = ?1", nativeQuery = true)
	List<LocacaoItens> listarLocacaoItens(Long idLocacao);

}
