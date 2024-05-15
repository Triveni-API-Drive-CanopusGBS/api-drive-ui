import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScopeOfsupplyComponent } from './scope-ofsupply.component';

describe('ScopeOfsupplyComponent', () => {
  let component: ScopeOfsupplyComponent;
  let fixture: ComponentFixture<ScopeOfsupplyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScopeOfsupplyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScopeOfsupplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
