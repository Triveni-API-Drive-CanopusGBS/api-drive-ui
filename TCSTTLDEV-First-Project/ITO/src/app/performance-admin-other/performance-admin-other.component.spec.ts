import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceAdminOtherComponent } from './performance-admin-other.component';

describe('PerformanceAdminOtherComponent', () => {
  let component: PerformanceAdminOtherComponent;
  let fixture: ComponentFixture<PerformanceAdminOtherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PerformanceAdminOtherComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PerformanceAdminOtherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
