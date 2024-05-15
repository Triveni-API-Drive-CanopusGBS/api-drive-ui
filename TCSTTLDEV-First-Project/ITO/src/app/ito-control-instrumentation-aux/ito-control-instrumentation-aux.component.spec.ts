import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoControlInstrumentationAuxComponent } from './ito-control-instrumentation-aux.component';

describe('ItoControlInstrumentationAuxComponent', () => {
  let component: ItoControlInstrumentationAuxComponent;
  let fixture: ComponentFixture<ItoControlInstrumentationAuxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoControlInstrumentationAuxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoControlInstrumentationAuxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
