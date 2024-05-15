import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoVariableCostComponent } from './ito-variable-cost.component';

describe('ItoVariableCostComponent', () => {
  let component: ItoVariableCostComponent;
  let fixture: ComponentFixture<ItoVariableCostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoVariableCostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoVariableCostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
