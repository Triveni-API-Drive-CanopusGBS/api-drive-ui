import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminVarientCodeComponent } from './ito-admin-varient-code.component';

describe('ItoAdminVarientCodeComponent', () => {
  let component: ItoAdminVarientCodeComponent;
  let fixture: ComponentFixture<ItoAdminVarientCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminVarientCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminVarientCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
