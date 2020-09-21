import { Component, OnInit } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { VeiculoDTO } from 'src/app/models/veiculo.model';

@Component({
  selector: 'app-veiculo-list',
  templateUrl: './veiculo-list.component.html',
  styleUrls: ['./veiculo-list.component.css']
})
export class VeiculoListComponent implements OnInit {

  veiculos: Array<VeiculoDTO> = [];
  currentVeiculo = null;
  currentIndex = -1;

  constructor(private veiculoService: VeiculoService) { }

  ngOnInit(): void {
    this.getAllVeiculos();
  }

  getAllVeiculos(): void {
    this.veiculoService.getAllVeiculos()
      .subscribe(
        veiculos => {
          this.veiculos = veiculos;
          console.log(veiculos);
        },
        error => {
          console.log(error);
        });
  }

  refresh(): void {
    this.getAllVeiculos();
    this.currentVeiculo = null;
    this.currentIndex = -1;
  }

  setCurrentVeiculo(veiculo, index): void {
    this.currentVeiculo = veiculo;
    this.currentIndex = index;
  }

  getStatus() {
    return this.currentVeiculo && this.currentVeiculo.vendido ? "Vendido" : "Disponível";
  }
}
