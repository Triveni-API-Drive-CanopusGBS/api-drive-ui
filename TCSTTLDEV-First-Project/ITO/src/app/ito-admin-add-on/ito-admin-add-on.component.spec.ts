import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminAddOnComponent } from './ito-admin-add-on.component';

describe('ItoAdminAddOnComponent', () => {
  let component: ItoAdminAddOnComponent;
  let fixture: ComponentFixture<ItoAdminAddOnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminAddOnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminAddOnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
