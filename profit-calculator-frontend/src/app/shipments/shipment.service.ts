import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { IncomeRequestDto } from '../shared/models/income-request.dto';
import { CostRequestDto } from '../shared/models/cost-request.dto';
import { ShipmentResponseDto } from '../shared/models/shipment-response.dto';

@Injectable({
  providedIn: 'root'
})
export class ShipmentService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  createShipment(): Observable<ShipmentResponseDto> {
    return this.http.post<ShipmentResponseDto>(`${this.apiUrl}/api/v1/shipment`, {});
  }

  createIncome(dto: IncomeRequestDto): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/v1/income`, dto);
  }

  createCost(dto: CostRequestDto): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/v1/cost`, dto);
  }

  getAllShipments(): Observable<ShipmentResponseDto[]> {
    return this.http.get<ShipmentResponseDto[]>(`${this.apiUrl}/api/v1/shipment`);
  }

  getProfit(shipmentId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/v1/profit-loss/${shipmentId}`);
  }

}
