import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DboMechAuxialriesComponent } from './dbo-mech-auxialries.component';

describe('DboMechAuxialriesComponent', () => {
  let component: DboMechAuxialriesComponent;
  let fixture: ComponentFixture<DboMechAuxialriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DboMechAuxialriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DboMechAuxialriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
