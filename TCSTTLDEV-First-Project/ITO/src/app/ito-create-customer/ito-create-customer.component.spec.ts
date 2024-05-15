import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCreateCustomerComponent } from './ito-create-customer.component';

describe('ItoCreateCustomerComponent', () => {
  let component: ItoCreateCustomerComponent;
  let fixture: ComponentFixture<ItoCreateCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCreateCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCreateCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
