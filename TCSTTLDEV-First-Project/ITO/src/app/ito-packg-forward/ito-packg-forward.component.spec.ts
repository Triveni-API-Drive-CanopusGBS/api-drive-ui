import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoPackgForwardComponent } from './ito-packg-forward.component';

describe('ItoPackgForwardComponent', () => {
  let component: ItoPackgForwardComponent;
  let fixture: ComponentFixture<ItoPackgForwardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoPackgForwardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoPackgForwardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
