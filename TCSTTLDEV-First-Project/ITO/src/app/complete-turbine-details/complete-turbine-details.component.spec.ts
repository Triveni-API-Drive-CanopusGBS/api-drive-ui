import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompleteTurbineDetailsComponent } from './complete-turbine-details.component';

describe('CompleteTurbineDetailsComponent', () => {
  let component: CompleteTurbineDetailsComponent;
  let fixture: ComponentFixture<CompleteTurbineDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompleteTurbineDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompleteTurbineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
