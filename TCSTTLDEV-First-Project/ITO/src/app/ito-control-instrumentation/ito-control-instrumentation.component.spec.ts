import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoControlInstrumentationComponent } from './ito-control-instrumentation.component';

describe('ItoControlInstrumentationComponent', () => {
  let component: ItoControlInstrumentationComponent;
  let fixture: ComponentFixture<ItoControlInstrumentationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoControlInstrumentationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoControlInstrumentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
