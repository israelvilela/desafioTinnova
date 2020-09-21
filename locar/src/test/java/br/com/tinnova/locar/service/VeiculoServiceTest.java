package br.com.tinnova.locar.service;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.tinnova.locar.dto.VeiculoDTO;
import br.com.tinnova.locar.exception.BusinessException;
import br.com.tinnova.locar.model.Veiculo;
import br.com.tinnova.locar.repositorio.VeiculoRepository;

@RunWith(MockitoJUnitRunner.class)
public class VeiculoServiceTest {
	
	@Mock
	VeiculoRepository repository;
	
	@Mock
	ModelMapper modelMapper;
	
	@Spy
	@InjectMocks
	VeiculoService service;

	@Test(expected = BusinessException.class)
	public void save_deve_retornar_exception_quando_nao_enviar_dto() {
		//Arrange
		VeiculoDTO dto = null;
		
		//Act
		service.save(dto);
	}
	
	@Test
	public void save_deve_salvar_quando_dto_for_valido() {
		//Arrange
		Veiculo veiculo = new Veiculo();
		
		
		VeiculoDTO dto = new VeiculoDTO();
		dto.setMarca("Fiat");
		dto.setAno(1990);
		dto.setVeiculo("Punto");
		dto.setDescricao("Completo");
		
		veiculo = modelMapper.map(dto, Veiculo.class);
		
		Mockito.when(repository.save(veiculo)).thenReturn(Mockito.any());
		
		//Act
		service.save(dto);
		
		// Assert
		Mockito.verify(repository, Mockito.times(1)).save(veiculo);
	}
	
	@Test(expected = BusinessException.class)
	public void update_deve_retornar_exception_quando_veiculo_nao_existir() {
		//Arrange
		Integer id = 1;
		VeiculoDTO dto = new VeiculoDTO();
		
		Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
		
		//Act
		service.update(id, dto);
	}
	
	@Test(expected = BusinessException.class)
	public void update_deve_retornar_exception_quando_dto_for_nulo() {
		//Arrange
		Integer id = 1;
		VeiculoDTO dto = null;
		
		Mockito.when(repository.findById(id)).thenReturn(Optional.of(new Veiculo()));
		
		//Act
		service.update(id, dto);
	}
	
	
	@Test
	public void update_deve_atualizar_quando_veiculo_existir_e_dto_valido() {
		//Arrange
		VeiculoDTO dto = new VeiculoDTO();
		dto.setId(1);
		dto.setMarca("Fiat");
		dto.setAno(1990);
		
		Veiculo veiculo = new Veiculo();
		veiculo.setId(1);
		veiculo.setMarca("Fiat");
		veiculo.setAno(2000);
		
		Mockito.when(repository.findById(dto.getId())).thenReturn(Optional.of(veiculo));
		
		veiculo = modelMapper.map(dto, Veiculo.class);

		//Act
		service.update(dto.getId(), dto);
		
		//Assert
		Mockito.verify(repository, Mockito.times(1)).save(veiculo);
	}
	
	@Test(expected = BusinessException.class)
	public void verificarMarca_deve_retornar_exception_quando_nao_informar_marca() {
		// Arrange
		VeiculoDTO dto = new VeiculoDTO();

		// Act
		service.verificarMarca(dto);
	}
	
	@Test(expected = BusinessException.class)
	public void verificarMarca_deve_retornar_exception_quando_nao_encontrar_marca() {
		// Arrange
		VeiculoDTO dto = new VeiculoDTO();
		dto.setMarca("TESTE");

		// Act
		service.verificarMarca(dto);
	}
	
	@Test
	public void verificarMarca_nao_deve_retornar_exception_quando_encontrar_marca() {
		// Arrange
		VeiculoDTO dto = new VeiculoDTO();
		dto.setMarca("FIAT");

		// Act
		service.verificarMarca(dto);
	}
	
}
