import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExclusionAdminComponent } from './exclusion-admin.component';

describe('ExclusionAdminComponent', () => {
  let component: ExclusionAdminComponent;
  let fixture: ComponentFixture<ExclusionAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExclusionAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExclusionAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
