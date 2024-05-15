import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoGeneralInputsComponent } from './ito-general-inputs.component';

describe('ItoGeneralInputsComponent', () => {
  let component: ItoGeneralInputsComponent;
  let fixture: ComponentFixture<ItoGeneralInputsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoGeneralInputsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoGeneralInputsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
