import { Component, OnInit, ViewChild } from '@angular/core';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { VeiculoDTO } from 'src/app/models/veiculo.model';
import { ToastrService } from 'ngx-toastr';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-veiculo-details',
  templateUrl: './veiculo-details.component.html',
  styleUrls: ['./veiculo-details.component.css']
})
export class VeiculoDetailsComponent implements OnInit {

  @ViewChild("DashboardComponent")
  dashboard: DashboardComponent;

  currentVeiculo: VeiculoDTO;
  message = '';

  constructor(
    private veiculoService: VeiculoService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.message = '';
    this.getVeiculo(this.route.snapshot.paramMap.get('id'));
  }

  getVeiculo(id): void {
    this.veiculoService.getVeiculoBy(id)
      .subscribe(
        veiculo => {
          this.currentVeiculo = veiculo;
          console.log(veiculo);
        },
        error => {
          console.log(error);
        });
  }

  setVendido(status): void {
    this.currentVeiculo.vendido = status;
  }

  atualizarVeiculo(): void {
    this.veiculoService.atualizar(this.currentVeiculo.id, this.currentVeiculo)
      .subscribe(
        response => {
          this.toastr.success("Veículo atualizado com sucesso!");
          this.router.navigate(['/veiculos']);
          this.dashboard.atualizarTotais();
        },
        error => {
          console.log(error);
        });
  }

  deletar(): void {
    this.veiculoService.deletar(this.currentVeiculo.id)
      .subscribe(
        response => {
          this.toastr.success("Veículo deletado com sucesso!")
          this.router.navigate(['/veiculos']);
          this.dashboard.atualizarTotais();
        },
        error => {
          console.log(error);
        });
  }

  getStatus() {
    return this.currentVeiculo && this.currentVeiculo.vendido ? "Vendido" : "Disponível";
  }
}
