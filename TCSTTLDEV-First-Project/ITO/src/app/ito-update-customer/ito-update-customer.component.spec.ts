import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdateCustomerComponent } from './ito-update-customer.component';

describe('ItoUpdateCustomerComponent', () => {
  let component: ItoUpdateCustomerComponent;
  let fixture: ComponentFixture<ItoUpdateCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdateCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdateCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
