import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipmentProfitlossComponent } from './shipment-profitloss.component';

describe('ShipmentProfitlossComponent', () => {
  let component: ShipmentProfitlossComponent;
  let fixture: ComponentFixture<ShipmentProfitlossComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShipmentProfitlossComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShipmentProfitlossComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
