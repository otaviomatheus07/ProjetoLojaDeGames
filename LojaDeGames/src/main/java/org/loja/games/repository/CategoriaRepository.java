package org.loja.games.repository;

import java.util.List;

import org.loja.games.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public List<Categoria> findAllByDescricaoContainingIgnoreCase
	(@Param("descricao") String descricao);
	

}
