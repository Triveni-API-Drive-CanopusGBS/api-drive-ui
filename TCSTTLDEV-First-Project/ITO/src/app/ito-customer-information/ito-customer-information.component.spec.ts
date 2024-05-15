import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCustomerInformationComponent } from './ito-customer-information.component';

describe('ItoCustomerInformationComponent', () => {
  let component: ItoCustomerInformationComponent;
  let fixture: ComponentFixture<ItoCustomerInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCustomerInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCustomerInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
