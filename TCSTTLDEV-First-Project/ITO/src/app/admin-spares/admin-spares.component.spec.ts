import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSparesComponent } from './admin-spares.component';

describe('AdminSparesComponent', () => {
  let component: AdminSparesComponent;
  let fixture: ComponentFixture<AdminSparesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSparesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSparesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
