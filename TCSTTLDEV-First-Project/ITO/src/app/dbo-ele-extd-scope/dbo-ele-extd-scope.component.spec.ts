import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DboEleExtdScopeComponent } from './dbo-ele-extd-scope.component';

describe('DboEleExtdScopeComponent', () => {
  let component: DboEleExtdScopeComponent;
  let fixture: ComponentFixture<DboEleExtdScopeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DboEleExtdScopeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DboEleExtdScopeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
