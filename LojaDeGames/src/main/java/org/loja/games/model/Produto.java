package org.loja.games.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // faz virar uma tabela no banco de dados
@Table(name = "tb_jogos") // cria o nome da tabela do banco de dados
public class Produto {
	@Id // funciona como o primary key

	@GeneratedValue(strategy = GenerationType.IDENTITY) // Funciona como o auto_increment
	private Long id;
	
	@NotNull // Funciona para não deixar o campo em branco
	@Size(min = 5, max = 100) // define a quantidade de caracteres
	public String titulo;
	
	@NotNull // Funciona para não deixar o campo em branco
	@Size(min = 10, max = 500) // define a quantidade de caracteres
	public String descricao;
	
	@UpdateTimestamp
	private LocalDate data;
	
	@ManyToOne // Relacionamento de muitos para um.
	@JsonIgnoreProperties("categoria") // impede o Looping infinito
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
