import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoScopeOfSupplyComponent } from './ito-scope-of-supply.component';

describe('ItoScopeOfSupplyComponent', () => {
  let component: ItoScopeOfSupplyComponent;
  let fixture: ComponentFixture<ItoScopeOfSupplyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoScopeOfSupplyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoScopeOfSupplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
