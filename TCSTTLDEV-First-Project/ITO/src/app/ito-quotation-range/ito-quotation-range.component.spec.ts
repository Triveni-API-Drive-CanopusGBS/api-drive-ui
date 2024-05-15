import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoQuotationRangeComponent } from './ito-quotation-range.component';

describe('ItoQuotationRangeComponent', () => {
  let component: ItoQuotationRangeComponent;
  let fixture: ComponentFixture<ItoQuotationRangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoQuotationRangeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoQuotationRangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
