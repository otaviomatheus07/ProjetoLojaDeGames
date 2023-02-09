package org.loja.games.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_usuariosJogo")
public class Usuario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@NotNull(message = "O atributo Nome è obrigatório! ")// Não deixa nada em branco
	private String nome;
	
	@NotNull(message = "O atributo Nome è obrigatório! ") // Não deixa nada em branco
	@Email(message = "O atributo Usuário è obrigatório!")// Define que tem que ser um email valido
	private String usuario;
	
	@NotBlank(message = "O atributo Senha é obrigatório!")// Faz com que nao fique em branco
	@Size(min = 8, message = "A senha deve conter mínimo 8 caracteres")
	private String senha;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
}
