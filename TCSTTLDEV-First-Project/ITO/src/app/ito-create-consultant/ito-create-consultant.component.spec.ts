import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCreateConsultantComponent } from './ito-create-consultant.component';

describe('ItoCreateConsultantComponent', () => {
  let component: ItoCreateConsultantComponent;
  let fixture: ComponentFixture<ItoCreateConsultantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCreateConsultantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCreateConsultantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
