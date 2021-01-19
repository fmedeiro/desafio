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
	@Column(unique = true, length=11)
	private String cpf;
	
	@NotNull
	private LocalDateTime dataNascimento;
	
	@NotNull
	private String nome;
	
	private LocalDateTime dataCadastro;
	private LocalDateTime dataUltimaAlteracao;
	private String email;
	private String nacionalidade;
	private String naturalidade;
	private SexEnum sexo;	
}
