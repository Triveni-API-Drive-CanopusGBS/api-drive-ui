import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DboEleAuxialriesComponent } from './dbo-ele-auxialries.component';

describe('DboEleAuxialriesComponent', () => {
  let component: DboEleAuxialriesComponent;
  let fixture: ComponentFixture<DboEleAuxialriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DboEleAuxialriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DboEleAuxialriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
