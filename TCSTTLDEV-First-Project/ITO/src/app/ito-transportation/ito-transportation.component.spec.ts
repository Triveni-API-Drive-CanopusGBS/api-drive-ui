import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoTransportationComponent } from './ito-transportation.component';

describe('ItoTransportationComponent', () => {
  let component: ItoTransportationComponent;
  let fixture: ComponentFixture<ItoTransportationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoTransportationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoTransportationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
