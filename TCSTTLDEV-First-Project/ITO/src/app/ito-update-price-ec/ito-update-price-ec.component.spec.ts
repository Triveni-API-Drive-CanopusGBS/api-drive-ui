import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdatePriceEcComponent } from './ito-update-price-ec.component';

describe('ItoUpdatePriceEcComponent', () => {
  let component: ItoUpdatePriceEcComponent;
  let fixture: ComponentFixture<ItoUpdatePriceEcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdatePriceEcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdatePriceEcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
