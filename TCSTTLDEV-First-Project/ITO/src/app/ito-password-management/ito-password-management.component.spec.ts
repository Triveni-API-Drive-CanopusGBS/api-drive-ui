import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoPasswordManagementComponent } from './ito-password-management.component';

describe('ItoPasswordManagementComponent', () => {
  let component: ItoPasswordManagementComponent;
  let fixture: ComponentFixture<ItoPasswordManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoPasswordManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoPasswordManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
