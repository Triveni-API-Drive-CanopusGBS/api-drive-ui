import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DboEleCiextdScopeComponent } from './dbo-ele-ciextd-scope.component';

describe('DboEleCiextdScopeComponent', () => {
  let component: DboEleCiextdScopeComponent;
  let fixture: ComponentFixture<DboEleCiextdScopeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DboEleCiextdScopeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DboEleCiextdScopeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
