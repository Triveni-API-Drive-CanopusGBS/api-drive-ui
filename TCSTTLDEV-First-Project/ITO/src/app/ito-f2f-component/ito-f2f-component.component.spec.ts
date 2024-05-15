import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoF2fComponentComponent } from './ito-f2f-component.component';

describe('ItoF2fComponentComponent', () => {
  let component: ItoF2fComponentComponent;
  let fixture: ComponentFixture<ItoF2fComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoF2fComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoF2fComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
