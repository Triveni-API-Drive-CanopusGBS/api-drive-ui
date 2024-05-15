import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoSapresComponentComponent } from './ito-sapres-component.component';

describe('ItoSapresComponentComponent', () => {
  let component: ItoSapresComponentComponent;
  let fixture: ComponentFixture<ItoSapresComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoSapresComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoSapresComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
