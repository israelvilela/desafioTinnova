import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {VeiculoListComponent} from './components/veiculo-list/veiculo-list.component';
import {VeiculoDetailsComponent} from './components/veiculo-details/veiculo-details.component';
import {VeiculoCreateComponent} from './components/veiculo-create/veiculo-create.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: 'veiculos', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'veiculos', component: VeiculoListComponent },
  { path: 'veiculos/:id', component: VeiculoDetailsComponent },
  { path: 'novo', component: VeiculoCreateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
