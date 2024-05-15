import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminScopeOfSupplyComponent } from './ito-admin-scope-of-supply.component';

describe('ItoAdminScopeOfSupplyComponent', () => {
  let component: ItoAdminScopeOfSupplyComponent;
  let fixture: ComponentFixture<ItoAdminScopeOfSupplyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminScopeOfSupplyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminScopeOfSupplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
