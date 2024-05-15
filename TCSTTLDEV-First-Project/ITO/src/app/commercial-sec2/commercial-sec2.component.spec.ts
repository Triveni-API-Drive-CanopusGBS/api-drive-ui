import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommercialSec2Component } from './commercial-sec2.component';

describe('CommercialSec2Component', () => {
  let component: CommercialSec2Component;
  let fixture: ComponentFixture<CommercialSec2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommercialSec2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommercialSec2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
