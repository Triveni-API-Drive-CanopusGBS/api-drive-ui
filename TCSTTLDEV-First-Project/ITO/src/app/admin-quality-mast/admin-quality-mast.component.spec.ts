import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminQualityMastComponent } from './admin-quality-mast.component';

describe('AdminQualityMastComponent', () => {
  let component: AdminQualityMastComponent;
  let fixture: ComponentFixture<AdminQualityMastComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminQualityMastComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminQualityMastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
