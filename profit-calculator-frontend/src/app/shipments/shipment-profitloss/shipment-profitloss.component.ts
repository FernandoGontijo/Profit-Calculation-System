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
  profitLossList: ProfitLossResponseDto[] = [];
  currentPage = 0;
  totalPages = 1;
  pageSize = 5;
  incomeId: any;
  costId: any;

  isEditing = false;
  originalIncome: any;
  originalCost: any;
  originalAdditionalCost: any;

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
        this.isEditing = false;
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
  if (this.isSubmitting || this.shipmentForm.invalid || !this.selectedShipmentId) return;

  this.isSubmitting = true;
  this.errorMessage = '';

  const { income, cost, additionalCost } = this.shipmentForm.value;

  if (this.isEditing) {
    const updates: Promise<any>[] = [];

    const incomeChanged = income !== this.originalIncome;
    const newTotalCost = cost + additionalCost;
    const originalTotalCost = this.originalCost + this.originalAdditionalCost;
    const costChanged = newTotalCost !== originalTotalCost;

    if (incomeChanged && this.incomeId) {
      updates.push(
        this.shipmentService
          .updateIncome({
            id: this.incomeId,
            shipmentId: this.selectedShipmentId,
            amount: income,
          })
          .toPromise()
      );
    }

    if (costChanged && this.costId) {
      updates.push(
        this.shipmentService
          .updateCost({
            id: this.costId,
            shipmentId: this.selectedShipmentId,
            amount: newTotalCost,
          })
          .toPromise()
      );
    }

    if (incomeChanged || costChanged) {
      updates.push(
        this.shipmentService.postProfit(this.selectedShipmentId).toPromise()
      );
    }

    if (updates.length === 0) {
      this.isSubmitting = false;
      return;
    }

    Promise.all(updates)
      .then(() => {
        this.reloadProfitLoss();
        this.shipmentForm.reset();
        this.readonly = true;
        this.isEditing = false;
      })
      .catch(() => {
        this.errorMessage = 'Failed to update data.';
      })
      .finally(() => {
        this.isSubmitting = false;
      });

  } else {
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
          this.shipmentForm.reset();
          this.readonly = true;
        },
        error: () => {
          this.errorMessage = 'Failed to calculate profit or loss.';
        },
        complete: () => {
          this.isSubmitting = false;
        },
      });
  }
}



  reloadProfitLoss(): void {
    this.shipmentService
      .getProfitLosses(this.currentPage, this.pageSize)
      .subscribe({
        next: (data) => {
          this.profitLossList = data.content;
          this.totalPages = data.totalPages;
        },
        error: () => (this.errorMessage = 'Failed to reload profit/loss'),
      });
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

  onEdit(item: ProfitLossResponseDto): void {
    this.selectedShipmentId = item.shipmentId;
    this.errorMessage = '';

    this.isEditing = true;
    this.readonly = true;

    this.shipmentService.getIncomeByShipmentId(item.shipmentId).subscribe({
      next: (income) => {
        this.shipmentService.getCostByShipmentId(item.shipmentId).subscribe({
          next: (cost) => {
            this.shipmentForm.patchValue({
              income: income.amount,
              cost: cost.amount,
              additionalCost: cost.additionalCost,
            });

            this.originalIncome = income.amount;
            this.originalCost = cost.amount;
            this.originalAdditionalCost = cost.additionalCost;
            this.incomeId = income.id;
            this.costId = cost.id;

            this.readonly = false;
          },
          error: () => {
            this.errorMessage = 'Failed to load cost data.';
            this.isEditing = false;
          },
        });
      },
      error: () => {
        this.errorMessage = 'Failed to load income data.';
        this.isEditing = false;
      },
    });
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
