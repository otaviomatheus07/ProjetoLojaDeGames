package org.loja.games.repository;

import java.util.List;

import org.loja.games.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
	// busca tudo que estiver dentro da variavel

}
