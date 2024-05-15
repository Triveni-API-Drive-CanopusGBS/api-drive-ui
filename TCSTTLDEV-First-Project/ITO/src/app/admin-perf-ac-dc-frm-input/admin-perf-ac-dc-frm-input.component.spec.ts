import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPerfAcDcFrmInputComponent } from './admin-perf-ac-dc-frm-input.component';

describe('AdminPerfAcDcFrmInputComponent', () => {
  let component: AdminPerfAcDcFrmInputComponent;
  let fixture: ComponentFixture<AdminPerfAcDcFrmInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPerfAcDcFrmInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPerfAcDcFrmInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
