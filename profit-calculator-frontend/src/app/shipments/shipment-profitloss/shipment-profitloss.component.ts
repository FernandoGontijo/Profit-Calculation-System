import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfitLossResponseDto } from '../../shared/models/profit-loss-response.dto';
import { ShipmentService } from '../shipment.service';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-shipment-profitloss',
  templateUrl: './shipment-profitloss.component.html',
  styleUrls: ['./shipment-profitloss.component.scss'],
})
export class ShipmentProfitlossComponent implements OnInit {
  shipmentForm: FormGroup;
  selectedShipmentId: number = 0;
  isManager = true;
  readonly = true;
  canCreate = true;
  errorMessage = '';
  isSubmitting = false;
  calculatedProfit: number | null = null;

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
    this.checkIfUserIsManager();
    this.loadProfitLosses();
  }

checkIfUserIsManager(): void {
  const token = localStorage.getItem('token');
  if (!token) return;

  try {
    const base64Url = token.split('.')[1]; 
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const payload = JSON.parse(decodeURIComponent(atob(base64).split('').map(function(c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join('')));

    this.isManager = payload?.roles?.includes('ROLE_MANAGER');

    console.log('Decoded JWT:', payload);
    console.log('Is Manager?', this.isManager);

  } catch (error) {
    console.error('Failed to decode JWT manually', error);
  }
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
        error: () => {
          this.errorMessage = 'Failed to load profit/loss data.';
        },
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
    if (this.shipmentForm.invalid || !this.selectedShipmentId) return;

    this.isSubmitting = true;
    this.errorMessage = '';

    const { income, cost, additionalCost } = this.shipmentForm.value;

    this.shipmentService
      .createIncome({
        shipmentId: this.selectedShipmentId,
        amount: income,
        id: 0,
      })
      .pipe(
        switchMap(() =>
          this.shipmentService.createCost({
            shipmentId: this.selectedShipmentId,
            amount: cost + additionalCost,
            id: 0,
          })
        ),
        switchMap(() =>
          this.shipmentService.postProfit(this.selectedShipmentId)
        )
      )
      .subscribe({
        next: () => {
          this.reloadProfitLoss();
          this.calculatedProfit = null;
        },
        error: () => {
          this.errorMessage = 'Failed to calculate profit or loss.';
        },
        complete: () => {
          this.isSubmitting = false;
        },
      });
  }

  reloadProfitLoss(): void {
    this.shipmentService
      .getProfitLosses(this.currentPage, this.pageSize)
      .subscribe({
        next: (data) => {
          this.profitLossList = data.content;
          this.totalPages = data.totalPages;
        },
        error: () => {
          this.errorMessage = 'Failed to reload profit/loss';
        },
      });
  }

  onSelectShipment(item: ProfitLossResponseDto): void {
    this.selectedShipmentId = item.shipmentId;
    this.calculatedProfit = item.calculatedProfit;
    this.shipmentForm.patchValue({
      income: item.totalIncome,
      cost: item.totalCost,
      additionalCost: 0,
    });
    this.readonly = true;
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
