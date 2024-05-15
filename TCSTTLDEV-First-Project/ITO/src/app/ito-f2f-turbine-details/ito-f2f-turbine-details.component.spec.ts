import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoF2fTurbineDetailsComponent } from './ito-f2f-turbine-details.component';

describe('ItoF2fTurbineDetailsComponent', () => {
  let component: ItoF2fTurbineDetailsComponent;
  let fixture: ComponentFixture<ItoF2fTurbineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoF2fTurbineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoF2fTurbineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
