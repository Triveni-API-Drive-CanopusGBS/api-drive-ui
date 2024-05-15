import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCostDbomechPriceComponent } from './update-cost-dbomech-price.component';

describe('UpdateCostDbomechPriceComponent', () => {
  let component: UpdateCostDbomechPriceComponent;
  let fixture: ComponentFixture<UpdateCostDbomechPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCostDbomechPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCostDbomechPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
