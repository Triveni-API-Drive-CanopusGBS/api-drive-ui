import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAddNewQuestionsComponent } from './ito-add-new-questions.component';

describe('ItoAddNewQuestionsComponent', () => {
  let component: ItoAddNewQuestionsComponent;
  let fixture: ComponentFixture<ItoAddNewQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAddNewQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAddNewQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
