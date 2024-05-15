import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminCurrencyConvComponent } from './ito-admin-currency-conv.component';

describe('ItoAdminCurrencyConvComponent', () => {
  let component: ItoAdminCurrencyConvComponent;
  let fixture: ComponentFixture<ItoAdminCurrencyConvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminCurrencyConvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminCurrencyConvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
