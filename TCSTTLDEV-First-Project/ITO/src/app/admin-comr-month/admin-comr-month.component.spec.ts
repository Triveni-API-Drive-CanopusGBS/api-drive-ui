import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminComrMonthComponent } from './admin-comr-month.component';

describe('AdminComrMonthComponent', () => {
  let component: AdminComrMonthComponent;
  let fixture: ComponentFixture<AdminComrMonthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminComrMonthComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminComrMonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
