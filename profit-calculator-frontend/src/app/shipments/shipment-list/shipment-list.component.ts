import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ProfitLossResponseDto } from '../../shared/models/profit-loss-response.dto';
import { ShipmentService } from '../shipment.service';

@Component({
  selector: 'app-shipment-list',
  templateUrl: './shipment-list.component.html',
  styleUrls: ['./shipment-list.component.scss'],
})
export class ShipmentListComponent {
  @Input() profitLossList: ProfitLossResponseDto[] = [];
  @Input() currentPage = 0;
  @Input() totalPages = 1;
  @Input() isManager = false;

  @Output() shipmentSelected = new EventEmitter<ProfitLossResponseDto>();
  @Output() previousPage = new EventEmitter<void>();
  @Output() nextPage = new EventEmitter<void>();
  @Output() selectShipment = new EventEmitter<ProfitLossResponseDto>();
  @Output() editShipment = new EventEmitter<ProfitLossResponseDto>();
  @Output() shipmentDeleted = new EventEmitter<void>();

  errorMessage: string = '';

  constructor(private shipmentService: ShipmentService) {}

  onPreviousPage(): void {
    this.previousPage.emit();
  }

  onNextPage(): void {
    this.nextPage.emit();
  }

  onEdit(item: ProfitLossResponseDto): void {
    this.editShipment.emit(item);
  }

  onRowClick(item: ProfitLossResponseDto): void {
    this.selectShipment.emit(item);
  }

  onDelete(item: ProfitLossResponseDto): void {
    if (
      confirm(`Are you sure you want to delete shipment ${item.shipmentId}?`)
    ) {
      this.shipmentService.deleteProfit(item.shipmentId).subscribe({
        next: () => {
          this.shipmentDeleted.emit();
        },
        error: () => {
          this.errorMessage = `Failed to delete shipment ${item.shipmentId}`;
        },
      });
    }
  }
}
