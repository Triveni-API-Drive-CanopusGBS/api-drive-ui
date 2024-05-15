import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommercialSec1Component } from './commercial-sec1.component';

describe('CommercialSec1Component', () => {
  let component: CommercialSec1Component;
  let fixture: ComponentFixture<CommercialSec1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommercialSec1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommercialSec1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
