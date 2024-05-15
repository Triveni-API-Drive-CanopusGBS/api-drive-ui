import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSubSupplierComponent } from './admin-sub-supplier.component';

describe('AdminSubSupplierComponent', () => {
  let component: AdminSubSupplierComponent;
  let fixture: ComponentFixture<AdminSubSupplierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSubSupplierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSubSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
