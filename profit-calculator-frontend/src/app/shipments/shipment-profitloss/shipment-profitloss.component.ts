import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfitLossResponseDto } from '../../shared/models/profit-loss-response.dto';

@Component({
  selector: 'app-shipment-profitloss',
  templateUrl: './shipment-profitloss.component.html',
  styleUrls: ['./shipment-profitloss.component.scss']
})
export class ShipmentProfitlossComponent {
  shipmentForm: FormGroup;
  selectedShipmentId: number | null = null;
  isManager = true;
  readonly = true;
  canCreate = true;
  errorMessage = '';

  profitLossList: ProfitLossResponseDto[] = [];
  currentPage = 0;
  totalPages = 1;

  constructor(private fb: FormBuilder) {
    this.shipmentForm = this.fb.group({
      income: [0, [Validators.required]],
      cost: [0, [Validators.required]],
      additionalCost: [0, [Validators.required]],
    });
  }

  onNewShipment(): void {
    this.selectedShipmentId = Math.floor(Math.random() * 10000);
    this.readonly = false;
    this.errorMessage = '';
    this.shipmentForm.reset({
      income: 0,
      cost: 0,
      additionalCost: 0
    });
  }

  onSubmit(): void {
    if (this.shipmentForm.invalid) return;

    const { income, cost, additionalCost } = this.shipmentForm.value;
    const totalCost = cost + additionalCost;
    const calculatedProfit = income - totalCost;

    const result: ProfitLossResponseDto = {
      shipmentId: this.selectedShipmentId!,
      totalIncome: income,
      totalCost,
      calculatedProfit
    };

    this.profitLossList.unshift(result);
    this.readonly = true;
  }

  previousPage(): void {
    if (this.currentPage > 0) this.currentPage--;
  }

  nextPage(): void {
    if (this.currentPage + 1 < this.totalPages) this.currentPage++;
  }
}
