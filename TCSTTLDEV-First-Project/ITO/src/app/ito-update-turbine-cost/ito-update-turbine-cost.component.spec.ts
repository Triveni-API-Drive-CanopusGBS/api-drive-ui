import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdateTurbineCostComponent } from './ito-update-turbine-cost.component';

describe('ItoUpdateTurbineCostComponent', () => {
  let component: ItoUpdateTurbineCostComponent;
  let fixture: ComponentFixture<ItoUpdateTurbineCostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdateTurbineCostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdateTurbineCostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
