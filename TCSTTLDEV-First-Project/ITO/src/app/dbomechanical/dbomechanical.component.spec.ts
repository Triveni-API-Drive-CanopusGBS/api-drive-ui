import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DBOMechanicalComponent } from './dbomechanical.component';

describe('DBOMechanicalComponent', () => {
  let component: DBOMechanicalComponent;
  let fixture: ComponentFixture<DBOMechanicalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DBOMechanicalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DBOMechanicalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
