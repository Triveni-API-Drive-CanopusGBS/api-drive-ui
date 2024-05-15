import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceAdminComponent } from './performance-admin.component';

describe('PerformanceAdminComponent', () => {
  let component: PerformanceAdminComponent;
  let fixture: ComponentFixture<PerformanceAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PerformanceAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PerformanceAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
