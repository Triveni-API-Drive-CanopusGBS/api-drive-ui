import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCustomerHomeComponent } from './ito-customer-home.component';

describe('ItoCustomerHomeComponent', () => {
  let component: ItoCustomerHomeComponent;
  let fixture: ComponentFixture<ItoCustomerHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCustomerHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCustomerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
