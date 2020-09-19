package br.com.tinnova.locar.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "veiculo") 
	private String veiculo;
	
	@Column(name = "marca") 
	private String marca;
	
	@Column(name = "ano") 
	private Integer ano;
	
	@Column(name = "descricao") 
	private String descricao;
	
	@Column(name = "vendido") 
	private Boolean vendido;
	
	@Column(name = "created") 
	private LocalDateTime created;
	
	@Column(name = "updated") 
	private LocalDateTime updated;
}
