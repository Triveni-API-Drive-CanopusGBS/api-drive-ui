import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MechExtendedScopeComponent } from './mech-extended-scope.component';

describe('MechExtendedScopeComponent', () => {
  let component: MechExtendedScopeComponent;
  let fixture: ComponentFixture<MechExtendedScopeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MechExtendedScopeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MechExtendedScopeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
