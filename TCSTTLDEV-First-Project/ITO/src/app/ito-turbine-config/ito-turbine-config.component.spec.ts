import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoTurbineConfigComponent } from './ito-turbine-config.component';

describe('ItoTurbineConfigComponent', () => {
  let component: ItoTurbineConfigComponent;
  let fixture: ComponentFixture<ItoTurbineConfigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoTurbineConfigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoTurbineConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
