import { Component, OnInit } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { VeiculoDTO } from 'src/app/models/veiculo.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public totalPorMarca: number;
  public totalPorDecada: number;
  public totalDisponiveis: number;
  public veiculosSemana: Array<VeiculoDTO> = [];

  constructor(private veiculoService: VeiculoService) { }

  ngOnInit(): void {
    this.atualizarTotais();
  }

  getTotalPorMarca() {
    this.veiculoService.getTotalPorMarca().subscribe(total => this.totalPorMarca = total);
  }

  getTotalPorDecada() {
    this.veiculoService.getTotalPorDecada().subscribe(total => this.totalPorDecada = total);
  }

  getTotalDisponiveis() {
    this.veiculoService.getTotalDisponiveis().subscribe(total => this.totalDisponiveis = total);
  }

  getTotalVeiculoSemana() {
    this.veiculoService.getTotalSemana().subscribe(veiculos => this.veiculosSemana = veiculos)
  }

  atualizarTotais() {
    this.getTotalPorMarca();
    this.getTotalPorDecada();
    this.getTotalDisponiveis();
    this.getTotalVeiculoSemana();
  }
}
