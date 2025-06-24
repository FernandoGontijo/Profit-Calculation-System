import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfitLossResponseDto } from '../../shared/models/profit-loss-response.dto';
import { ShipmentService } from '../shipment.service';

@Component({
  selector: 'app-shipment-profitloss',
  templateUrl: './shipment-profitloss.component.html',
  styleUrls: ['./shipment-profitloss.component.scss'],
})
export class ShipmentProfitlossComponent implements OnInit {
  shipmentForm: FormGroup;
  selectedShipmentId: number | null = null;
  isManager = true;
  readonly = true;
  canCreate = true;
  errorMessage = '';

  profitLossList: ProfitLossResponseDto[] = [];
  currentPage = 0;
  totalPages = 1;
  pageSize = 5;

  constructor(
    private fb: FormBuilder,
    private shipmentService: ShipmentService
  ) {
    this.shipmentForm = this.fb.group({
      income: [0, [Validators.required]],
      cost: [0, [Validators.required]],
      additionalCost: [0, [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.loadProfitLosses();
  }

  loadProfitLosses(): void {
    this.shipmentService
      .getProfitLosses(this.currentPage, this.pageSize)
      .subscribe({
        next: (data) => {
          this.profitLossList = data.content;
          this.totalPages = data.totalPages;
          this.currentPage = data.number;
        },
        error: () => (this.errorMessage = 'Failed to load profit/loss data.'),
      });
  }

  onNewShipment(): void {
    this.shipmentService.createShipment().subscribe({
      next: (shipmentId) => {
        this.selectedShipmentId = shipmentId;
        this.readonly = false;
        this.errorMessage = '';
        this.shipmentForm.reset({
          income: 0,
          cost: 0,
          additionalCost: 0,
        });
      },
      error: () => {
        this.errorMessage = 'Failed to create shipment.';
      },
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
      calculatedProfit,
    };

    this.profitLossList.unshift(result);
    this.readonly = true;
  }

  onSelectShipment(item: ProfitLossResponseDto): void {
    this.selectedShipmentId = item.shipmentId;
    this.shipmentForm.patchValue({
      income: item.totalIncome,
      cost: item.totalCost,
      additionalCost: 0,
    });
    this.readonly = true;
  }

  onEditShipment(item: ProfitLossResponseDto): void {
    this.selectedShipmentId = item.shipmentId;
    this.shipmentForm.patchValue({
      income: item.totalIncome,
      cost: item.totalCost,
      additionalCost: 0,
    });
    this.readonly = false;
  }

  onShipmentSelected(p: ProfitLossResponseDto): void {
    this.selectedShipmentId = p.shipmentId;
    this.readonly = !this.isManager;

    this.shipmentForm.patchValue({
      income: p.totalIncome,
      cost: p.totalCost,
      additionalCost: 0,
    });
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadProfitLosses();
    }
  }

  nextPage(): void {
    if (this.currentPage + 1 < this.totalPages) {
      this.currentPage++;
      this.loadProfitLosses();
    }
  }
}
