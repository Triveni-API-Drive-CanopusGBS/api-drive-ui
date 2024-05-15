import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPerfAcDcMastComponent } from './admin-perf-ac-dc-mast.component';

describe('AdminPerfAcDcMastComponent', () => {
  let component: AdminPerfAcDcMastComponent;
  let fixture: ComponentFixture<AdminPerfAcDcMastComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPerfAcDcMastComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPerfAcDcMastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
