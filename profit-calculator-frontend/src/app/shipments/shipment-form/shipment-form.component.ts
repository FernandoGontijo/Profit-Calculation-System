import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-shipment-form',
  templateUrl: './shipment-form.component.html',
  styleUrls: ['./shipment-form.component.scss'],
})
export class ShipmentFormComponent {
  @Input() selectedShipmentId: number | null = null;
  @Input() canCreate = false;
  @Input() isManager = false;
  @Input() errorMessage = '';
  @Input() form!: FormGroup;
  @Input() readonly = true;
  @Input() isSubmitting: boolean = false;

  @Output() newShipment = new EventEmitter<void>();
  @Output() submitForm = new EventEmitter<void>();

  onNewShipmentClick(): void {
    this.newShipment.emit();
  }

  onSubmitClick(): void {
    if (!this.readonly && !this.isSubmitting) {
      this.submitForm.emit();
    }
  }
}
