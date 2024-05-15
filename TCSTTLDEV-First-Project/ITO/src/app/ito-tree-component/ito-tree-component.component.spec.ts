import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoTreeComponentComponent } from './ito-tree-component.component';

describe('ItoTreeComponentComponent', () => {
  let component: ItoTreeComponentComponent;
  let fixture: ComponentFixture<ItoTreeComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoTreeComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoTreeComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
