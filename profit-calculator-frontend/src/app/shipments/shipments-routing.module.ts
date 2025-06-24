import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShipmentProfitlossComponent } from './shipment-profitloss/shipment-profitloss.component';

const routes: Routes = [
  { path: '', component: ShipmentProfitlossComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShipmentsRoutingModule {}
