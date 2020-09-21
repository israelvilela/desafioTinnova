package br.com.tinnova.locar.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.tinnova.locar.dto.VeiculoDTO;
import br.com.tinnova.locar.dto.VeiculoDecada;
import br.com.tinnova.locar.dto.VeiculoFabricante;
import br.com.tinnova.locar.exception.BusinessException;
import br.com.tinnova.locar.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoApi {
	
	@Autowired
	private VeiculoService service;

	@GetMapping
	public List<VeiculoDTO> getAllVeiculos() {
		return service.getAllVeiculos();
	}
	
	@GetMapping(path = "/disponiveis")
	public Integer getAllVeiculosDisponiveis() {
		return service.veiculosDisponiveis();
	}
	
	@GetMapping(path = "/total-marca")
	public List<VeiculoFabricante> getTotalPorMarca() {
		return service.totalVeiculosPorMarca();
	}
	
	@GetMapping(path = "/total-decada")
	public List<VeiculoDecada> getTotalPorDecada() {
		return service.totalVeiculosPorDecada();
	}
	
	@GetMapping(path = "/total-semana")
	public List<VeiculoDTO> getTotalSemana() {
		return service.getTotalSemana();
	}
	
	
	@GetMapping(path = "/{idVeiculo}")
	public VeiculoDTO getVeiculoBy(@PathVariable("idVeiculo") Integer idVeiculo) {
		return service.getVeiculoBy(idVeiculo);
	}
	
	@PostMapping
	public void salvar(@RequestBody VeiculoDTO dto) {
		try {
			service.save(dto);
		} catch (BusinessException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@PutMapping(path = "/{id}")
	public void atualizar(@PathVariable("id") Integer idVeiculo, @RequestBody VeiculoDTO dto) {
		service.update(idVeiculo, dto);
	}
	
	@DeleteMapping(path = "/{idVeiculo}")
	public void deletar(@PathVariable("idVeiculo") Integer idVeiculo) {
		service.delete(idVeiculo);
	}
	
}
