import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShipmentFormComponent } from './shipment-form/shipment-form.component';
import { ShipmentListComponent } from './shipment-list/shipment-list.component';
import { ShipmentProfitlossComponent } from './shipment-profitloss/shipment-profitloss.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ShipmentsRoutingModule } from './shipments-routing.module';

@NgModule({
  declarations: [
    ShipmentFormComponent,
    ShipmentListComponent,
    ShipmentProfitlossComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    ShipmentsRoutingModule 
  ]
})
export class ShipmentsModule { }
