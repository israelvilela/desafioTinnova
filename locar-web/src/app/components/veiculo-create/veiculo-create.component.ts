import { Component, OnInit, ViewChild } from '@angular/core';
import { VeiculoDTO } from 'src/app/models/veiculo.model';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-veiculo-create',
  templateUrl: './veiculo-create.component.html',
  styleUrls: ['./veiculo-create.component.css']
})
export class VeiculoCreateComponent implements OnInit {

  @ViewChild("DashboardComponent")
  dashboard: DashboardComponent;

  submitted = false;
  veiculoDTO: VeiculoDTO = new VeiculoDTO();

  constructor(private veiculoService: VeiculoService) { }

  ngOnInit(): void {
  }

  salvar(): void {
    this.veiculoService.salvar(this.veiculoDTO)
      .subscribe(
        response => {
          this.submitted = true;
          this.dashboard.atualizarTotais();
        },
        error => {
          console.log(error);
        });
  }

  novoVeiculo(): void {
    this.submitted = false;
    this.veiculoDTO = new VeiculoDTO();
  }
}
