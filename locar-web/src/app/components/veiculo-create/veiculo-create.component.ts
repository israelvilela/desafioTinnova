import { Component, OnInit, ViewChild } from '@angular/core';
import { VeiculoDTO } from 'src/app/models/veiculo.model';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { ToastrService } from 'ngx-toastr';
import { isNullOrUndefined } from 'util';

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

  constructor(private veiculoService: VeiculoService,
              private toast: ToastrService) { }

  ngOnInit(): void {
  }

  salvar(): void {
    let isValid = this.validarCampos();

    if (isValid) {
      this.veiculoService.salvar(this.veiculoDTO)
      .subscribe(
        response => {
          this.submitted = true;
          this.dashboard.atualizarTotais();
          this.toast.success("Veículo cadastrado com sucesso.");
        },
        error => {
          console.log(error);
        });
    }
  }

  validarCampos(): boolean {
    let campos = [];
    let isValid = true;

    if (isNullOrUndefined(this.veiculoDTO.veiculo)) {
      isValid = false;
      campos.push('Veículo');
    } if (isNullOrUndefined(this.veiculoDTO.ano)) {
      isValid = false;
      campos.push('Ano');
    } if (isNullOrUndefined(this.veiculoDTO.marca)) {
      isValid = false;
      campos.push('Marca');
    } if (isNullOrUndefined(this.veiculoDTO.descricao)) {
      isValid = false;
      campos.push('Descrição');
    }

    this.toast.error(`O preenchimento dos campos ${campos.map(campo => campo)} é obrigatório!`);

    return isValid;
  }

  novoVeiculo(): void {
    this.submitted = false;
    this.veiculoDTO = new VeiculoDTO();
  }
}
