package br.com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.locadora.entity.Filme;

@Repository
public interface RepositoryFilme extends JpaRepository<Filme, Long> {

	@Query("SELECT f FROM Filme f where f.idFilme in (:listFilmes)")
	List<Filme> listFilmes(@Param("listFilmes") List<Long> listFilmes);
	
	
	@Transactional
	@Modifying
	@Query("update Filme f set f.isDisponivel = 0 where f.idFilme in (:listFilmes)")
	void devolverFilmes(@Param("listFilmes") List<Filme> listFilme);

}
