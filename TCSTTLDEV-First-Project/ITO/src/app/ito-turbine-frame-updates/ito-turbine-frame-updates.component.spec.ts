import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoTurbineFrameUpdatesComponent } from './ito-turbine-frame-updates.component';

describe('ItoTurbineFrameUpdatesComponent', () => {
  let component: ItoTurbineFrameUpdatesComponent;
  let fixture: ComponentFixture<ItoTurbineFrameUpdatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoTurbineFrameUpdatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoTurbineFrameUpdatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
