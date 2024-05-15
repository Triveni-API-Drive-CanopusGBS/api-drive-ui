import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoTurbineQuestionsComponent } from './ito-turbine-questions.component';

describe('ItoTurbineQuestionsComponent', () => {
  let component: ItoTurbineQuestionsComponent;
  let fixture: ComponentFixture<ItoTurbineQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoTurbineQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoTurbineQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
