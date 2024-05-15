import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminServiceMastComponent } from './admin-service-mast.component';

describe('AdminServiceMastComponent', () => {
  let component: AdminServiceMastComponent;
  let fixture: ComponentFixture<AdminServiceMastComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminServiceMastComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminServiceMastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
