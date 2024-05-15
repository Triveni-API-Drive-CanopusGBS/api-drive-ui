import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAddFrameComponent } from './ito-add-frame.component';

describe('ItoAddFrameComponent', () => {
  let component: ItoAddFrameComponent;
  let fixture: ComponentFixture<ItoAddFrameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAddFrameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAddFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
