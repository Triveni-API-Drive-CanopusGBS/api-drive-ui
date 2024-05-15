import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCostEstimationComponent } from './ito-cost-estimation.component';

describe('ItoCostEstimationComponent', () => {
  let component: ItoCostEstimationComponent;
  let fixture: ComponentFixture<ItoCostEstimationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCostEstimationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCostEstimationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
