import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EstimateCostComponent } from './estimate-cost.component';

describe('EstimateCostComponent', () => {
  let component: EstimateCostComponent;
  let fixture: ComponentFixture<EstimateCostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EstimateCostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EstimateCostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
