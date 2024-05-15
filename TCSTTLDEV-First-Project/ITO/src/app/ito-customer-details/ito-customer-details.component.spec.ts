import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ItoCustomerDetailsComponent } from './ito-customer-details.component';



describe('ItoCustomerDetailsComponent', () => {
  let component: ItoCustomerDetailsComponent;
  let fixture: ComponentFixture<ItoCustomerDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCustomerDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCustomerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
