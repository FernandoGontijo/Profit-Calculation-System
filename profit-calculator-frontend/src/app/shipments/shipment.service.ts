import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { IncomeRequestDto } from '../shared/models/income-request.dto';
import { CostRequestDto } from '../shared/models/cost-request.dto';
import { ShipmentResponseDto } from '../shared/models/shipment-response.dto';
import { ProfitLossResponseDto } from '../shared/models/profit-loss-response.dto';
import { PaginatedResponse } from '../shared/models/paginated-response';

@Injectable({
  providedIn: 'root',
})
export class ShipmentService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  createShipment(): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/api/v1/shipment`, {});
  }

  createIncome(dto: IncomeRequestDto): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/v1/income`, dto);
  }

  updateIncome(dto: IncomeRequestDto): Observable<any> {
    return this.http.put(`${this.apiUrl}/api/v1/income`, dto);
  }

  createCost(dto: CostRequestDto): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/v1/cost`, dto);
  }

  updateCost(dto: CostRequestDto): Observable<any> {
    return this.http.put(`${this.apiUrl}/api/v1/cost`, dto);
  }

  getAllShipments(): Observable<ShipmentResponseDto[]> {
    return this.http.get<ShipmentResponseDto[]>(
      `${this.apiUrl}/api/v1/shipment`
    );
  }

  getProfit(shipmentId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/v1/profit-loss/${shipmentId}`);
  }

  deleteProfit(shipmentId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/api/v1/profit-loss/${shipmentId}`);
  }

  getProfitLosses(
    page: number = 0,
    size: number = 10
  ): Observable<PaginatedResponse<ProfitLossResponseDto>> {
    return this.http.get<PaginatedResponse<ProfitLossResponseDto>>(
      `${this.apiUrl}/api/v1/profit-loss?page=${page}&size=${size}`
    );
  }
}
