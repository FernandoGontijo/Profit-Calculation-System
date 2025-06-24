import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ProfitLossResponseDto } from '../../shared/models/profit-loss-response.dto';

@Component({
  selector: 'app-shipment-list',
  templateUrl: './shipment-list.component.html',
  styleUrls: ['./shipment-list.component.scss'],
})
export class ShipmentListComponent {
  @Input() profitLossList: ProfitLossResponseDto[] = [];
  @Input() currentPage = 0;
  @Input() totalPages = 1;

  @Output() previousPage = new EventEmitter<void>();
  @Output() nextPage = new EventEmitter<void>();

  onPreviousPage(): void {
    this.previousPage.emit();
  }

  onNextPage(): void {
    this.nextPage.emit();
  }
}
