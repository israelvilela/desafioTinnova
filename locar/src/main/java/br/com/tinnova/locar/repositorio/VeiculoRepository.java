package br.com.tinnova.locar.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tinnova.locar.dto.VeiculoDecada;
import br.com.tinnova.locar.dto.VeiculoFabricante;
import br.com.tinnova.locar.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{

	@Query("SELECT count(veiculo) FROM Veiculo veiculo WHERE veiculo.vendido = false ")
	Integer findVeiculosDisponiveis();
	
	@Query("select new br.com.tinnova.locar.dto.VeiculoFabricante(veiculo.marca, count(veiculo.id)) from  Veiculo veiculo group by veiculo.marca")
	List<VeiculoFabricante> totalVeiculosPorMarca(); 
	
	@Query(value = "select decada, count(*) as totalVeiculos  from (select floor(ano / 10) * 10 as decada from veiculo) t  group by decada ", nativeQuery = true)
	List<VeiculoDecada> totalVeiculosPorDecada();

	@Query("SELECT veiculo from Veiculo veiculo WHERE veiculo.created < :data")
	List<Veiculo> getTotalSemana(LocalDateTime data); 
}
