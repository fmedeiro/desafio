package com.envolti.desafio.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.envolti.desafio.enums.SexEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true, length=14)
	private String cpf;
	
	@NotNull
	private LocalDateTime dataNascimento;
	
	@NotNull
	@Column(length=100)
	private String nome;
	
	private LocalDateTime dataCadastro;
	private LocalDateTime dataUltimaAlteracao;
	
	@Column(length=50)
	private String email;
	
	@Column(length=50)
	private String nacionalidade;
	
	@Column(length=50)
	private String naturalidade;
	private SexEnum sexo;	
}
