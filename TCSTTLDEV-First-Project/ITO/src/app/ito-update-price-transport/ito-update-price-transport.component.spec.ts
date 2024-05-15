import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdatePriceTransportComponent } from './ito-update-price-transport.component';

describe('ItoUpdatePriceTransportComponent', () => {
  let component: ItoUpdatePriceTransportComponent;
  let fixture: ComponentFixture<ItoUpdatePriceTransportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdatePriceTransportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdatePriceTransportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
