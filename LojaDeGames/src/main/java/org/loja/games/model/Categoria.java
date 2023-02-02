package org.loja.games.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_categorias")
public class Categoria {
	@Id // define que uma chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;

	@NotNull(message = "O Atributo Descrição é obrigatório")
	private String descricao;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)// Relacionamento de um para muitos // MAPPEDBY: Define o lado proprietario do relacinamento
	// Cascade: Faz com que qualquer funcao feita, seja aplicada todas as entidades. // CascadeType.REMOVE: Tudo é removido quando algo é apagado.
	@JsonIgnoreProperties("categoria") //impede o looping infinito
	private List<Produto> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

}
