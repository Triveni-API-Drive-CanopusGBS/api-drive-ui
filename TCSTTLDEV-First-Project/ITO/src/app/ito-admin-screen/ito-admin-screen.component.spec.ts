import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminScreenComponent } from './ito-admin-screen.component';

describe('ItoAdminScreenComponent', () => {
  let component: ItoAdminScreenComponent;
  let fixture: ComponentFixture<ItoAdminScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
