import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminVehicleFrameLinkComponent } from './ito-admin-vehicle-frame-link.component';

describe('ItoAdminVehicleFrameLinkComponent', () => {
  let component: ItoAdminVehicleFrameLinkComponent;
  let fixture: ComponentFixture<ItoAdminVehicleFrameLinkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminVehicleFrameLinkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminVehicleFrameLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
