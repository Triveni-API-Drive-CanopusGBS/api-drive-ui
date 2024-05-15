import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DBOElectricalComponent } from './dboelectrical.component';

describe('DBOElectricalComponent', () => {
  let component: DBOElectricalComponent;
  let fixture: ComponentFixture<DBOElectricalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DBOElectricalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DBOElectricalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
