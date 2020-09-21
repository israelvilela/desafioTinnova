package br.com.tinnova.locar.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.tinnova.locar.dto.VeiculoDTO;
import br.com.tinnova.locar.dto.VeiculoDecada;
import br.com.tinnova.locar.dto.VeiculoFabricante;
import br.com.tinnova.locar.exception.BusinessException;
import br.com.tinnova.locar.model.Veiculo;
import br.com.tinnova.locar.repositorio.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private static final List<String> FABRICANTES = Arrays.asList("Fiat", "Ford", "Honda", "Volkswagen", "Peugeot");

	public VeiculoDTO getVeiculoBy(Integer id) {
		Optional<Veiculo> veiculo = repository.findById(id);
		
		return modelMapper.map(veiculo.get(), VeiculoDTO.class);
	}
	
	public List<VeiculoDTO> getAllVeiculos() {
		List<Veiculo> veiculos = repository.findAll(Sort.by(Sort.Direction.ASC, "ano"));

		return veiculos.stream().map(veiculo -> modelMapper.map(veiculo, VeiculoDTO.class)).collect(Collectors.toList());
	}
	
	public Integer veiculosDisponiveis() {
		return repository.findVeiculosDisponiveis();
	}
	
	public List<VeiculoFabricante> totalVeiculosPorMarca() {
		return repository.totalVeiculosPorMarca();
	}
	
	public List<VeiculoDecada> totalVeiculosPorDecada() {
		return repository.totalVeiculosPorDecada();
	}
	
	public List<VeiculoDTO> getTotalSemana() {
		LocalDateTime data = LocalDateTime.now();
		data = data.minusDays(7);
		
		List<Veiculo> veiculos = repository.getTotalSemana(data);
		
		return veiculos.stream().map(veiculo -> modelMapper.map(veiculo, VeiculoDTO.class)).collect(Collectors.toList());
	}
	
	public void save(VeiculoDTO dto) throws BusinessException {
		if (dto != null) {
			verificarMarca(dto);
			dto.setVendido(Boolean.FALSE);
			dto.setCreated(LocalDateTime.now());
			repository.save(modelMapper.map(dto, Veiculo.class));
		}
	}
	
	public void update(Integer id, VeiculoDTO dto) {
		Optional<Veiculo> veiculo = Optional.of(repository.findById(id)).orElseThrow(() -> new BusinessException("Veículo não existe!"));
		
		if (veiculo.isPresent() && dto != null) {
			dto.setUpdated(LocalDateTime.now());
			repository.save(modelMapper.map(dto, Veiculo.class));
		}

	}
	
	public void delete(Integer id) {
		if (id != null) {
			repository.deleteById(id);
		}
	}
	
	private void verificarMarca(VeiculoDTO dto) {
		if (!Strings.isEmpty(dto.getMarca())) {
			if (!FABRICANTES.contains(dto.getMarca())) {
				throw new BusinessException("Marca informada não existe.");
			}
		}
	}
}
