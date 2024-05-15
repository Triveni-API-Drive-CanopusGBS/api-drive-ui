import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAcwrListComponent } from './admin-acwr-list.component';

describe('AdminAcwrListComponent', () => {
  let component: AdminAcwrListComponent;
  let fixture: ComponentFixture<AdminAcwrListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAcwrListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAcwrListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
