import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoProfileManagementComponent } from './ito-profile-management.component';

describe('ItoUpdateUserComponent', () => {
  let component: ItoProfileManagementComponent;
  let fixture: ComponentFixture<ItoProfileManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoProfileManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoProfileManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
