import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-shipment-form',
  templateUrl: './shipment-form.component.html',
  styleUrls: ['./shipment-form.component.scss'],
})
export class ShipmentFormComponent {
  @Input() selectedShipmentId!: number | null;
  @Input() readonly = true;
  @Input() canCreate = true;
  @Input() isManager = false;
  @Input() form!: FormGroup;
  @Input() errorMessage = '';

  @Output() newShipment = new EventEmitter<void>();
  @Output() submitForm = new EventEmitter<void>();

  onNewShipmentClick(): void {
    this.newShipment.emit();
  }

  onSubmitClick(): void {
    this.submitForm.emit();
  }
}
