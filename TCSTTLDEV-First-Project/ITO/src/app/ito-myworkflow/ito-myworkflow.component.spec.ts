import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoMyworkflowComponent } from './ito-myworkflow.component';

describe('ItoMyworkflowComponent', () => {
  let component: ItoMyworkflowComponent;
  let fixture: ComponentFixture<ItoMyworkflowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoMyworkflowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoMyworkflowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
