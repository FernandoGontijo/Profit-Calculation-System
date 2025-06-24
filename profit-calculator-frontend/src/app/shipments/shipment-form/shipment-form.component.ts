import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ShipmentService } from '../shipment.service';
import { ShipmentResponseDto } from '../../shared/models/shipment-response.dto';
import { CostRequestDto } from '../../shared/models/cost-request.dto';
import { IncomeRequestDto } from '../../shared/models/income-request.dto';
import { ProfitLossResponseDto } from '../../shared/models/profit-loss-response.dto';

@Component({
  selector: 'app-shipment-form',
  templateUrl: './shipment-form.component.html',
  styleUrls: ['./shipment-form.component.scss'],
})
export class ShipmentFormComponent implements OnInit {
  shipmentForm!: FormGroup;
  shipments: ShipmentResponseDto[] = [];
  displayedColumns: string[] = ['shipmentId', 'income', 'totalCosts', 'profit'];
  errorMessage = '';
  profitLossList: ProfitLossResponseDto[] = [];
  currentPage = 0;
  totalPages = 0;
  pageSize = 10;


  constructor(private fb: FormBuilder, private shipmentService: ShipmentService) {}

  ngOnInit(): void {
    this.loadProfitLosses();
    this.initForm();
  }

  initForm(): void {
    this.shipmentForm = this.fb.group({
      income: [null, [Validators.required, Validators.min(0)]],
      cost: [null, [Validators.required, Validators.min(0)]],
      additionalCost: [0, [Validators.min(0)]],
    });
  }

  loadProfitLosses(): void {
    this.shipmentService.getProfitLosses(this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.profitLossList = data.content;
        this.totalPages = data.totalPages;
        this.currentPage = data.number;
      },
      error: () => this.errorMessage = 'Failed to load profit/loss data.',
    });
  }


  onSubmit(): void {
    if (this.shipmentForm.invalid) return;

    const { income, cost, additionalCost } = this.shipmentForm.value;

    this.shipmentService.createShipment().subscribe({
      next: (shipment) => {
        const shipmentId = shipment.id;

        const incomeDto: IncomeRequestDto = {
          shipmentId,
          amount: income,
        };

        const totalCost = cost + additionalCost;
        const costDto: CostRequestDto = {
          shipmentId,
          amount: totalCost,
        };

        this.shipmentService.createIncome(incomeDto).subscribe({
          next: () => {
            this.shipmentService.createCost(costDto).subscribe({
              next: () => {
                this.loadShipments();
                this.shipmentForm.reset();
              },
              error: () => this.errorMessage = 'Failed to save cost.',
            });
          },
          error: () => this.errorMessage = 'Failed to save income.',
        });
      },
      error: () => this.errorMessage = 'Failed to create shipment.',
    });
  }

  loadShipments(): void {
    this.shipmentService.getAllShipments().subscribe({
      next: (data) => this.shipments = data,
      error: () => this.errorMessage = 'Failed to load shipments.',
    });
  }

nextPage() {
  if (this.currentPage + 1 < this.totalPages) {
    this.currentPage++;
    this.loadProfitLosses();
  }
}

previousPage() {
  if (this.currentPage > 0) {
    this.currentPage--;
    this.loadProfitLosses();
  }
}


}
