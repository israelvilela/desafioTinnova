import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseURL = 'http://localhost:8080/api/veiculos';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  constructor(private httpClient: HttpClient) { }

  getAllVeiculos(): Observable<any> {
    return this.httpClient.get(baseURL);
  }

  getVeiculoBy(id): Observable<any> {
    return this.httpClient.get(`${baseURL}/${id}`);
  }

  getTotalPorMarca(): Observable<any> {
    return this.httpClient.get(`${baseURL}/total-marca`);
  }

  getTotalPorDecada(): Observable<any> {
    return this.httpClient.get(`${baseURL}/total-decada`);
  }

  getTotalDisponiveis(): Observable<any> {
    return this.httpClient.get(`${baseURL}/disponiveis`);
  }

  getTotalSemana(): Observable<any> {
    return this.httpClient.get(`${baseURL}/total-semana`);
  }

  salvar(data): Observable<any> {
    return this.httpClient.post(baseURL, data);
  }

  atualizar(id, data): Observable<any> {
    return this.httpClient.put(`${baseURL}/${id}`, data);
  }

  deletar(id): Observable<any> {
    return this.httpClient.delete(`${baseURL}/${id}`);
  }

  searchByName(name): Observable<any> {
    return this.httpClient.get(`${baseURL}?name=${name}`);
  }
}
