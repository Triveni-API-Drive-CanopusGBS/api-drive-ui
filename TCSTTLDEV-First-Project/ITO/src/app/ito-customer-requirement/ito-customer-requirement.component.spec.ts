import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCustomerRequirementComponent } from './ito-customer-requirement.component';

describe('ItoCustomerRequirementComponent', () => {
  let component: ItoCustomerRequirementComponent;
  let fixture: ComponentFixture<ItoCustomerRequirementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCustomerRequirementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCustomerRequirementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
