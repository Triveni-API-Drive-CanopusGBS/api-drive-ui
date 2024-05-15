import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminUpdateVehicleNoComponent } from './ito-admin-update-vehicle-no.component';

describe('ItoAdminUpdateVehicleNoComponent', () => {
  let component: ItoAdminUpdateVehicleNoComponent;
  let fixture: ComponentFixture<ItoAdminUpdateVehicleNoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminUpdateVehicleNoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminUpdateVehicleNoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
