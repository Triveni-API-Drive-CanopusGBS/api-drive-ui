import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlInstrumentationComponent } from './control-instrumentation.component';

describe('ControlInstrumentationComponent', () => {
  let component: ControlInstrumentationComponent;
  let fixture: ComponentFixture<ControlInstrumentationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ControlInstrumentationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ControlInstrumentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
