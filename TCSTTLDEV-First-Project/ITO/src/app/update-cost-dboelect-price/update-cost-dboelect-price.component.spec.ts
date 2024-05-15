import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCostDboelectPriceComponent } from './update-cost-dboelect-price.component';

describe('UpdateCostDboelectPriceComponent', () => {
  let component: UpdateCostDboelectPriceComponent;
  let fixture: ComponentFixture<UpdateCostDboelectPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCostDboelectPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCostDboelectPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
