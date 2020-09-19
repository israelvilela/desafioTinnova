package br.com.tinnova.locar.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VeiculoDTO {

	private Integer id;
	
	private String veiculo;
	
	private String marca;
	
	private Integer ano;
	
	private String descricao;
	
	private Boolean vendido;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
	
	private Long totalPorMarca;
	
	public VeiculoDTO() {
	}
	
	public VeiculoDTO(String marca, Long totalPorMarca) {
		this.marca = marca;
		this.totalPorMarca = totalPorMarca;
	}
}
